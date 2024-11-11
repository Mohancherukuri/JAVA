package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

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
        
        // Get the stored secret number from the session (if exists)
        HttpSession session = req.getSession();
        Integer secretNumber = (Integer) session.getAttribute("secretNumber");
        
        // If no secret number exists, generate a new one
        if (secretNumber == null) {
            secretNumber = rand.nextInt(100) + 1; // Random number between 1 and 100
            session.setAttribute("secretNumber", secretNumber);
            session.setAttribute("attempts", 0); // Initialize attempt count
        }
        
        // Get the user's guess (if submitted)
        String guessString = req.getParameter("guess");
        String message = "";
        
        if (guessString != null) {
            try {
                int guess = Integer.parseInt(guessString);
                int attempts = (Integer) session.getAttribute("attempts") + 1;
                session.setAttribute("attempts", attempts);

                // Compare the guess with the secret number
                message = compareGuess(guess, secretNumber, attempts, session);
            } catch (NumberFormatException e) {
                message = "Please enter a valid number.";
            }
        }
        
        // Output the HTML form and the game feedback message
        out.println("<html>");
        out.println("<head><title>Guess the Number Game</title></head>");
        out.println("<body>");
        out.println("<h2>Guess the Number Game!</h2>");
        
        if (!message.isEmpty()) {
            out.println("<p>" + message + "</p>");
        }
        
        out.println("<form method='GET'>");
        out.println("<label for='guess'>Enter your guess (1-100): </label>");
        out.println("<input type='text' id='guess' name='guess'>");
        out.println("<input type='submit' value='Guess'>");
        out.println("</form>");
        
        out.println("<br><br>");
        out.println("<p><a href=''>Try Again</a></p>"); // Refresh to reset game session
        
        out.println("</body>");
        out.println("</html>");
        
        out.close();
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
