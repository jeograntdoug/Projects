/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgLibrary;


enum SortBy {
    // "Member's name", "Book title", "Date loaned", "Date returned", "Popularity Of Book(total loans)"
    MemberName("Member's name"),
    BookTitle("Book title"),
    DateLoaned("Date loaned"),
    DateReturned("Date returned"),
    Popularity("<<WIP>>Popularity Of Book (total loans)");

    private String str;

    SortBy(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}

enum Show {
    //"Currently loaned", "Overdue only", "All past and present loans" 
    InLoan("Currently loaned"),
    OverDue("Overdue Only"),
    EverLoaded("All past and present loans");

    private String str;

    Show(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}