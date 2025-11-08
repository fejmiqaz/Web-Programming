package mk.finki.ukim.wp.model;

public class BookReservation {
    public String bookTitle;
    public String readerName;
    public String readerAddress;
    public long numberOfCopies;

    public BookReservation(String bookTitle, String readerName, String readerAddress, long numberOfCopies) {
        this.bookTitle = bookTitle;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.numberOfCopies = numberOfCopies;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getReaderName() {
        return readerName;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public long getNumberOfCopies() {
        return numberOfCopies;
    }
}
