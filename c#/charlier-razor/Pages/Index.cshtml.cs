using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mime;
using System.Threading.Tasks;
using CharlieRazor.Data;
using CharlieRazor.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;

namespace CharlieRazor.Pages
{
    public class IndexModel : PageModel
    {
        
        private readonly ApplicationDbContext _db;
        
        public IndexModel(ApplicationDbContext db) {
            _db = db;
        }
        
        [TempData]
        public string Message { get; set; }
        
        public IList<Event> Studyjna { get; private set; }
        public IList<Event> Kameralna { get; private set; }
        public IList<Event> Klubowa { get; private set; }
        public IList<Event> Arte { get; private set; }

        public async Task OnGetAsync()
        {
            Studyjna     = await _db.Events.Include(e => e.Film).Where(e => e.Hall == Hall.Studyjna).AsNoTracking().ToListAsync();
            Kameralna    = await _db.Events.Include(e => e.Film).Where(e => e.Hall == Hall.Kameralna).AsNoTracking().ToListAsync();
            Klubowa      = await _db.Events.Include(e => e.Film).Where(e => e.Hall == Hall.Klubowa).AsNoTracking().ToListAsync();
            Arte         = await _db.Events.Include(e => e.Film).Where(e => e.Hall == Hall.Arte).AsNoTracking().ToListAsync();
        }
    }
}