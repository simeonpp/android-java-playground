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
    { ISBN: 1, title: "To Kill a Mockingbird" },
    { ISBN: 2, title: "Pride and Prejudice" },
    { ISBN: 3, title: "The Book Thief" },
    { ISBN: 4, title: "Gone with the Wind" },
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

        if (!ISBN || !title) {
            return reply({error: 'Invalid params'});
        }

        var isbnExists = books.find((book) => {
            return book.ISBN === ISBN;
        });
        if (isbnExists) {
            return reply({error: "Book already exists"});
        }

        var bookToAdd = {ISBN, title}
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