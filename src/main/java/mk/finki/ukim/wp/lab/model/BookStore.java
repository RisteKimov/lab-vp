package mk.finki.ukim.wp.lab.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookStore {
    private Long id;
    private String name;
    private String city;
    private String address;
    private List<Book> books;

    public BookStore(String name, String city, String address) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.city = city;
        this.address = address;
        books = new ArrayList<>();
    }
    public void addBookToStore(Book b){
        books.add(b);
//        b.setBookStore(this);
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
    public boolean matchingId(long id){
        return this.id == id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book deleteBook(Long id)
    {
        for(Book b : books)
        {
            if(Objects.equals(b.getId(), id)) {
                books.remove(b);
                return b;
            }
        }
        return null;
    }
}
