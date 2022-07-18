using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CharlieRazor.Models
{
    public class Film
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [Required, StringLength(50)] public string Title { get; set; }

        [Required, StringLength(2048)] public string Description { get; set; }

        [Required, StringLength(50)] public string ProductionInfo { get; set; }

        [Required, StringLength(50)] public string Type { get; set; }

        [Required] public TimeSpan Duration { get; set; }
        
        public byte[] Image { get; set; }
    }
}