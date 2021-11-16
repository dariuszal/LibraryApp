# LibraryApp
LibraryApplication with JSON file storage implementation

###API Doumentation

https://documenter.getpostman.com/view/17991576/UVC9gkPq

### Short functionality description

###Functions

Book:create, delete, find all, search by all parameters.

User:create, find by email

Order: create

###Restrictions

Once ordered, books are added to User books array, also no longer avalable for taking by someone else.

One User can have not more than 3 books at a time. Book cannot be taken for longer than 2 months period.


Book cannot be deleted if any User has it.

###TODO
User function to return book/books.