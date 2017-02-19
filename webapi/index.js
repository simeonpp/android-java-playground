'use strict';

const Hapi = require('hapi');
const timeSimulatingNetworkLatency = 3500;

// Create a server with a host and port
const server = new Hapi.Server();
server.connection({ 
    host: '192.168.0.103', 
    port: 8000 
});

var books = [
    { ISBN: 1, title: "To Kill a Mockingbird", imageUrl: 'https://03fcd67fd51850d3ba6b-6cb392df11a341bce8c76b1898d0c030.ssl.cf3.rackcdn.com/large/9780/0994/9780099419785.jpg' },
    { ISBN: 2, title: "Pride and Prejudice", imageUrl: 'http://www.publicbookshelf.com/images/PridePrejudice423x630.jpg' },
    { ISBN: 3, title: "The Book Thief", imageUrl: 'https://upload.wikimedia.org/wikipedia/en/8/8f/The_Book_Thief_by_Markus_Zusak_book_cover.jpg' },
    { ISBN: 4, title: "Gone with the Wind", imageUrl: 'https://upload.wikimedia.org/wikipedia/en/6/6b/Gone_with_the_Wind_cover.jpg' },
];

// Add books route
server.route({
    method: 'GET',
    path:'/api/books', 
    handler: function (request, reply) {
        console.log("Fetching books");
        setTimeout(() => {
            // Simulate slow network
            return reply(books);
        }, timeSimulatingNetworkLatency);
    }
});

// Add book details route
server.route({
    method: 'GET',
    path:'/api/book/{id}', 
    handler: function (request, reply) {
        var bookId = +request.params.id;
        var book = books.find((book) => {
            return book.ISBN === bookId;
        });
        console.log("Getting book info: " + bookId);
        if (book) {
            setTimeout(() => {
            // Simulate slow network
                return reply(book);
            }, timeSimulatingNetworkLatency);
        } else {
            return reply({ISBN: 0, title: 'Unknown'});
        }
    }
});

// Add book create route
server.route({
    method: 'POST',
    path:'/api/book', 
    handler: function (request, reply) {        
        var ISBN = parseInt(request.payload.ISBN);
        var title = request.payload.title;
        var imageUrl = request.payload.imageUrl || 'http://www.publicbookshelf.com/images/PridePrejudice423x630.jpg';

        if (!ISBN || !title) {
            return reply({error: 'Invalid params'});
        }

        var isbnExists = books.find((book) => {
            return book.ISBN === ISBN;
        });
        if (isbnExists) {
            return reply({error: "Book already exists"});
        }

        var bookToAdd = {ISBN, title, imageUrl}
        books.push(bookToAdd);
        console.log("New book added");
        console.log(bookToAdd);
        setTimeout(() => {
            // Simulate slow network
            return reply(bookToAdd);
        }, timeSimulatingNetworkLatency);
    }
});

// Start the server
server.start((err) => {

    if (err) {
        throw err;
    }
    console.log('Server running at:', server.info.uri);
});