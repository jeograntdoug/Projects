
package pkgLibrary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {
    public Loan(
            int memberId, String memberName,
            String bookTitle, String bookAuthors,
            LocalDate dateBorrowed,
            LocalDate dueDate,
            LocalDate dateReturned
     )
    {
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookTitle = bookTitle;
        this.bookAuthors = bookAuthors;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dueDate;
        this.dateReturned = dateReturned;
    }
    
    private int id;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private LocalDate dateReturned;
    private String memberName;
    private int memberId;
    private String bookTitle;
    private String bookAuthors;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    @Override
    public String toString(){
        //"Jerry Boe (#2) borrowed \"The cooking bible\" by J.T. Cook on Dec 20, 2019, due Jan 20, 2020, not returned yet (overdue)"
        String dateReturnedStr ="";
        if(dateReturned == null){
            if(dueDate.compareTo(LocalDate.now()) < 0){
                dateReturnedStr = "not returned yet(overdue)";
            } else {
                dateReturnedStr = String.format(
                        "(%d days left)",
                        LocalDate.now().until(dueDate).getDays()
                );
            }
        } else{
            dateReturnedStr = dateReturned.format(dateFormat);
        }
        
        return String.format(
                "%s (#%d) borrowed \"%s\" by %s on %s, due %s, %s",
                memberName,
                memberId,
                bookTitle,
                bookAuthors,
                dateBorrowed.format(dateFormat),
                dueDate.format(dateFormat),
                dateReturnedStr
        );
    }
}
