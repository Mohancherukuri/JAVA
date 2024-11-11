package com.helloworld;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.GuessGame;

public class GuessGameTest {

    private GuessGame guessGame;
    private HttpSession session;

    @Before
    public void setUp() {
        // Initialize the servlet and mock the session
        guessGame = new GuessGame();
        session = mock(HttpSession.class);
        
        // Mock session attributes
        when(session.getAttribute("attempts")).thenReturn(0); // Starting with 0 attempts
    }

    @Test
    public void testCompareGuessTooLow() {
        // Secret number is 50, and user guesses 20 (which is too low)
        int secretNumber = 50;
        int guess = 20;
        int attempts = 10;
        
        // Call the method and get the result
        String result = guessGame.compareGuess(guess, secretNumber,attempts,session);
        
        // Assert the message is "Too low"
        assertEquals("Too low. Try again!", result);
    }

    @Test
    public void testCompareGuessTooHigh() {
        // Secret number is 50, and user guesses 80 (which is too high)
        int secretNumber = 50;
        int guess = 80;
        int attempts = 10;
        
        // Call the method and get the result
        String result = guessGame.compareGuess(guess, secretNumber,attempts,session);
        
        // Assert the message is "Too high"
        assertEquals("Too high. Try again!", result);
    }

    @Test
    public void testCompareGuessCorrect() {
        // Secret number is 50, and user guesses 50 (correct guess)
        int secretNumber = 50;
        int guess = 50;
        int attempts = 20;
        
        // Call the method and get the result
        String result = guessGame.compareGuess(guess, secretNumber,attempts,session);
        
        // Assert the message is "Correct"
        assertEquals("Correct! You guessed the number in 10 attempts.", result);
    }
}
