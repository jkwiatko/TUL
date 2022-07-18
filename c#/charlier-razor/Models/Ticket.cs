using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Identity;

namespace CharlieRazor.Models
{
    public class Ticket
    {

        public Ticket()
        {
            
        }
        public Ticket(IdentityUser user, Event @event, int row, int seat)
        {
            User = user;
            Event = @event;
            Row = row;
            Seat = seat;
        }
        
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
         public int Id { get; set; }
         
         [Required]
         public IdentityUser User { get; set; }
         
         [Required]
         public Event Event { get; set; }
         
         [Required, Range(1,10)]
         public int Row { get; set; }
         
         [Required, Range(1,10)]
         public int Seat { get; set; }
    }
}