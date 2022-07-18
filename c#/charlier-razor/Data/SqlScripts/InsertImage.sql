UPDATE Films SET Image = (SELECT(SELECT * FROM OPENROWSET(BULK N'/home/jakub/Pictures/GreenBook.jpg', SINGLE_BLOB) Image))
where Title = 'Green Book';