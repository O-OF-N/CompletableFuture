# CompletableFuture
This project demonstrates Completable futures introduced as a part of Java 8. It does so by trying to implement a REST client using the futures and then implementing the same client using Completable futures.

This project uses https://jsonplaceholder.typicode.com APIs to demonstrate.

The client does the following:
* Gets the list of users from this URL: https://jsonplaceholder.typicode.com/users
* Serializes user JSON response to User object
* Iterates over each user, get the list of posts made by each user using this URL https://jsonplaceholder.typicode.com/posts?userId=?
* Serializes post JSON response to Post object.
* Builds an UserPost object, which has the User object and the list of Posts made by the user.


To run this project:
 * Clone
 * Run `mvn install` 
 * Run `Main.java` file.