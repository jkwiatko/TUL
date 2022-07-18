using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Runtime.CompilerServices;
using System.Threading.Tasks;
using CharlieRazor.Areas.Identity.Pages.Account;
using CharlieRazor.Data;
using CharlieRazor.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Internal;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

namespace CharlieRazor.Pages
{
    public class Booking : PageModel
    {
        private ApplicationDbContext _db;
        private UserManager<IdentityUser> _manager;
        private static readonly object ticketLock = new object();
        public List<Ticket> BookedTickets { get; set; }
        public Event SelectedEvent { get; set; }

        [TempData]
        public String SelectedTicketsJson { get; set; }

        public Booking(ApplicationDbContext db, UserManager<IdentityUser> manager)
        {
            _db = db;
            _manager = manager;
        }
        
        public async Task OnGetAsync(int id)
        {
            SelectedTicketsJson = null;
            SelectedEvent = _db.Events.First(e => e.Id == id);
            BookedTickets = _db.Tickets.Where(t => t.Event.Id == id).ToList();
        }
        
        public void OnPostAddTicket(int row, int seat, int eventId)
        {
            SelectedEvent = _db.Events.First(e => e.Id == eventId);
            BookedTickets = _db.Tickets.Where(t => t.Event.Id == eventId).ToList();

            if (BookedTickets.Exists(ticket => ticket.Row == row && ticket.Seat == seat)) return;
            if (SelectedTicketsJson == null)
            {
                List<TicketCandidate> ticketCandidates = new List<TicketCandidate>();
                ticketCandidates.Add(new TicketCandidate(row, seat));
                SelectedTicketsJson = TicketCandidate.ToJson(ticketCandidates);
            }
            else
            {
                List<TicketCandidate> ticketCandidates  = TicketCandidate.FromJson(SelectedTicketsJson);
                ticketCandidates.Add(new TicketCandidate(row, seat));
                SelectedTicketsJson = TicketCandidate.ToJson(ticketCandidates);
            }
        }

        public IActionResult OnPostRemoveTicket(int row, int seat, int eventId)
        {
            SelectedEvent = _db.Events.First(e => e.Id == eventId);
            BookedTickets = _db.Tickets.Where(t => t.Event.Id == eventId).ToList();
            List<TicketCandidate> ticketCandidates  = TicketCandidate.FromJson(SelectedTicketsJson);
            ticketCandidates.RemoveAll(ticket => ticket.row == row && ticket.seat == seat);
            SelectedTicketsJson = TicketCandidate.ToJson(ticketCandidates);
            return Page();
        }

        public IActionResult OnPostConfirm(int eventId)
        {
            SelectedEvent = _db.Events.First(e => e.Id == eventId);
            BookedTickets = _db.Tickets.Where(t => t.Event.Id == eventId).ToList();
            
            if (SelectedTicketsJson != null)
            {
                List<TicketCandidate> ticketCandidates = TicketCandidate.FromJson(SelectedTicketsJson);
                SelectedEvent = _db.Events.First(e => e.Id == eventId);
                IdentityUser user = _db.Users.Find(_manager.GetUserId(User));
                
                lock (ticketLock)
                {
                    if (!ticketCandidates
                        .Exists(candiate => BookedTickets
                            .Exists(ticket => ticket.Row == candiate.row && ticket.Seat == candiate.seat)))
                    {
                        ticketCandidates.ForEach(candidate =>
                        {
                            _db.Tickets.Add((new Ticket(user, SelectedEvent, candidate.row, candidate.seat)));
                            _db.SaveChanges();
                        });
                        return RedirectToPage("./Booking");
                    }
                }
                
                ticketCandidates.RemoveAll(candidate => BookedTickets
                    .Exists(ticket => ticket.Row == candidate.row && ticket.Seat == candidate.seat));
                SelectedTicketsJson = TicketCandidate.ToJson(ticketCandidates);
            }
            return Page();
        }

        public class TicketCandidate
        {
            public TicketCandidate(int row, int seat)
            {
                this.row = row;
                this.seat = seat;
            }

            public static List<TicketCandidate> FromJson(String json)
            {
                if (json == null)
                {
                    return new List<TicketCandidate>();
                }
                return JsonConvert.DeserializeObject<List<TicketCandidate>>(json);
            }

            public static String ToJson(List<TicketCandidate> ticketCandidates)
            {
                return JsonConvert.SerializeObject(ticketCandidates);
            }
            
            
            public int row;
            public int seat;
        }
        
    }
}