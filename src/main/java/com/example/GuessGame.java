package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GuessGame extends HttpServlet {

    // Initialize a random number generator
    private Random rand = new Random();

    // Game logic to generate a random number and handle guesses
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    HttpSession session = req.getSession();
    Integer secretNumber = (Integer) session.getAttribute("secretNumber");
    
    if (secretNumber == null) {
        secretNumber = rand.nextInt(100) + 1; // Random number between 1 and 100
        session.setAttribute("secretNumber", secretNumber);
        session.setAttribute("attempts", 0);
    }
    
    String guessString = req.getParameter("guess");
    String message = "";
    
    if (guessString != null) {
        try {
            int guess = Integer.parseInt(guessString);
            int attempts = (Integer) session.getAttribute("attempts") + 1;
            session.setAttribute("attempts", attempts);
            message = compareGuess(guess, secretNumber, attempts, session);
        } catch (NumberFormatException e) {
            message = "Please enter a valid number.";
        }
    }
    
    // Set the message as a request attribute so it can be accessed in the JSP/HTML page
    req.setAttribute("message", message);
    
    // Forward to the HTML page (index.jsp)
    RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
    try {
        dispatcher.forward(req, res);
    } catch (ServletException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

    // Extracted method to compare the guess with the secret number
    public String compareGuess(int guess, int secretNumber, int attempts, HttpSession session) {
        if (guess < secretNumber) {
            return "Too low. Try again!";
        } else if (guess > secretNumber) {
            return "Too high. Try again!";
        } else {
            session.removeAttribute("secretNumber"); // Reset game after correct guess
            session.removeAttribute("attempts");
            return "Correct! You guessed the number in " + attempts + " attempts.";
        }
    }
}
