///////////////////////////////////////////////////////////////////////////////
//                   
// Main Class File:  Awari.java
// File:             studentAI.java
// Semester:         CS 540 Spring 2018
//
// Author:           Meiliu Wu, mwu233@wisc.edu
// Lecturer's Name:  Chuck Dyer
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////

/**
 * (This is the studentAI file with Alpha-beta Algorithm to 
 *  play the game Awari. Note that the maxDepth can be set 
 *  before running the game. Assume maxDepth will not exceed
 *  15, given the 10-second time limit to calculate each move.)
 *
 * <p>Bugs: No bugs are tested out.
 *
 * @author Meiliu Wu
 */
public class studentAI extends Player {
    private int maxDepth;
    
    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
    
    /**
     * a wrapper function to call the alpha-beta search to get the best move
     *
     * @param state the current state 
     */
    public void move(BoardState state) {
    	// cut off the search at some fixed depth limit, which is specified by the maxDepth class member
    	// In the Match class, there is a 10-second time limit to calculate each move
    	move = alphabetaSearch(state, maxDepth);

    }
    
    /**
     * returns an int value indicating the best move pit index.
     *
     * @param state the current state 
     * @param maxDepth cutoff value during the search at some fixed depth limit
     * @return the best move pit index 
     */
    public int alphabetaSearch(BoardState state, int maxDepth) {
    	 
    	// test maxValue
    	//int test_v = minValue(state,maxDepth,2,Integer.MIN_VALUE,Integer.MAX_VALUE);
    	//System.out.println("return: " + test_v);

    	int v = Integer.MIN_VALUE;
    	int MaxIndex = -1;
    	for(int i = 0; i < 6; i++) { 
    		// To get the max from all legal (min) children nodes, recording the index of max_so_far
    		if (state.isLegalMove(1, i)) {
    			// current depth starts at maxDepth and decreases to 0
    			int v_next = minValue(state.applyMove(1, i),maxDepth,maxDepth-1,Integer.MIN_VALUE,Integer.MAX_VALUE);
    			if (v < v_next) {
    				v = v_next;
    				MaxIndex = i;
    			}
    		}
    	}
    	return MaxIndex;
    	
    }

    /**
     * returns sbe value (a utility value) for a max node
     *
     * @param state the current state 
     * @param maxDepth cutoff value during the search at some fixed depth limit
     * @param currentDepth the depth of the current max node
     * @param alpha the value of the best choice (i.e., highest value) so far that at any choice point along the path for the MAX node
     * @param beta the value of the best choice (i.e., lowest value) so far that at any choice point along the path for the MIN node
     * @return sbe value (a utility value) for a max node
     */
    public int maxValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {
    	
    	// if terminal is tested, then return the utility value of state
    	if (currentDepth == 0 || state.status(1) != BoardState.GAME_NOT_OVER) {
    		return sbe(state);
    	}
    	
    	int v = Integer.MIN_VALUE;
    	
    	for (int i = 0; i < 6; i++) {
    		if (state.isLegalMove(1, i)) {
    			BoardState newState = state.applyMove(1, i);
        		v = Math.max(v,minValue(newState,maxDepth,currentDepth-1, alpha, beta));
        		if (v >= beta) { //cutoff
        			return v;
        		}
        		alpha = Math.max(alpha, v);
    		}
    	}
    	return v;
    }

    /**
     * returns sbe value (a utility value) for a min node
     *
     * @param state the current state 
     * @param maxDepth cutoff value during the search at some fixed depth limit
     * @param currentDepth the depth of the current min node
     * @param alpha the value of the best choice (i.e., highest value) so far that at any choice point along the path for the MAX node
     * @param beta the value of the best choice (i.e., lowest value) so far that at any choice point along the path for the MIN node
     * @return sbe value (a utility value) for a min node
     */
    public int minValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {

    	// if terminal is tested, then return the utility value of state
    	if (currentDepth == 0 || state.status(2) != BoardState.GAME_NOT_OVER) {
    		return sbe(state);
    	}
    	
    	int v = Integer.MAX_VALUE;
    	
    	for (int i = 0; i < 6; i++) {
    		if (state.isLegalMove(2, i)) {
    			BoardState newState = state.applyMove(2, i);
    			v = Math.min(v,maxValue(newState,maxDepth,currentDepth-1, alpha, beta));
        		if (alpha >= v) { //cutoff
        			return v;
        		}
        		beta = Math.min(beta, v);
    		}
    	}
    	return v;
    }

    /**
     * returns a board state's sbe value.
     * Return the number of stones in the storehouse of the current player minus the number of stones in the opponent’s storehouse.
     * @param state the current state
     * @return a board state's sbe value.
     */
    public int sbe(BoardState state){
    	return (state.score[0] - state.score[1]); //player1's # of stone in storehouse minus player2's
    }


}