'use strict';

const Hapi = require('hapi');

// Create a server with a host and port
const server = new Hapi.Server();
server.connection({ 
    host: 'localhost', 
    port: 8000 
});

var books = [
    { id: 1, title: "To Kill a Mockingbird" },
    { id: 2, title: "Pride and Prejudice" },
    { id: 3, title: "The Book Thief" },
    { id: 4, title: "Gone with the Wind" },
];

// Add books route
server.route({
    method: 'GET',
    path:'/api/books', 
    handler: function (request, reply) {
        return reply(books);
    }
});

// Add book details route
server.route({
    method: 'GET',
    path:'/api/book/{id}', 
    handler: function (request, reply) {
        var bookId = +request.params.id;
        var book = books.find((book) => {
            return book.id === bookId;
        });
        if (book) {
            return reply(book);
        } else {
            return reply({id: 0, title: 'Unknown'});
        }
    }
});

// Start the server
server.start((err) => {

    if (err) {
        throw err;
    }
    console.log('Server running at:', server.info.uri);
});