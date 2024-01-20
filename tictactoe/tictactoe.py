"""
Tic Tac Toe Player
"""

import math
import copy

X = "X"
O = "O"
EMPTY = None

count = 0
MIN, MAX = float('-inf'), float('inf')

def initial_state():
    """
    Returns starting state of the board.
    """
    return [[EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY]]


def player(board):
    """
    Returns player who has the next turn on a board.
    """
    total_x = 0
    total_o = 0

    for i in range(0, 3):
        for j in range(0, 3):
            if board[i][j] == X:
                total_x += 1
            elif board[i][j] == O:
                total_o += 1
    
    if total_x > total_o:
        return O
    else:
        return X



def actions(board):
    """
    Returns set of all possible actions (i, j) available on the board.
    """

    action_list = set()
    rows = 3
    columns = 3
    for i in range(columns):
        for j in range(rows):
            if board[i][j] == EMPTY:
                action_list.add((i, j))

    return action_list


def result(board, action):
    """
    Returns the board that results from making move (i, j) on the board.
    """
    if action[0]<0 or action[1] >3 or action[1] <0 or action[1]>3:
        raise IndexError

    resulting_board = copy.deepcopy(board)
    resulting_board[action[0]][action[1]] = player(board)
    
    return resulting_board



def winner(board):
    """
    Returns the winner of the game, if there is one.
    """
    if board[0][0] != EMPTY and board[0][0] == board[0][1] and board[0][1] == board[0][2]:
        return board[0][0]
    elif board[1][0] != EMPTY and board[1][0] == board[1][1] and board[1][1] == board[1][2]:
        return board[1][0]
    elif board[2][0] != EMPTY and board[2][0] == board[2][1] and board[2][1] == board[2][2]:
        return board[2][0]
    elif board[0][0] != EMPTY and board[0][0] == board[1][0] and board[1][0] == board[2][0]:
        return board[0][0]
    elif board[0][1] != EMPTY and board[0][1] == board[1][1] and board[1][1] == board[2][1]:
        return board[0][1]
    elif board[0][2] != EMPTY and board[0][2] == board[1][2] and board[1][2] == board[2][2]:
        return board[0][2]
    elif board[0][0] != EMPTY and board[0][0] == board[1][1] and board[1][1] == board[2][2]:
        return board[0][0]
    elif board[0][2] != EMPTY and board[0][2] == board[1][1] and board[1][1] == board[2][0]:
        return board[0][2]
    else:
        return None


def tie(board):
    for row in board:
        for box in row:
            if box == EMPTY:
                return False
    return True


def terminal(board):
    """
    Returns True if game is over, False otherwise.
    """

    if winner(board) != None or tie(board):
        return True
    else:
        False


def utility(board):
    """
    Returns 1 if X has won the game, -1 if O has won, 0 otherwise.
    """
    
    winner_player = winner(board)
    if winner_player == X:
        return 1
    elif winner_player == O:
        return -1
    else:
        return 0

def maximize(board, alpha, beta):
    global count
    count+=1
    best_so_far = MIN
    if terminal(board):
        return utility(board)
    
    move = float('-inf')
    for action in actions(board):
        move = max(move, minimize(result(board, action), alpha, beta))
        best_so_far = max(best_so_far, move)
        alpha = max(alpha, best_so_far)

        if beta<=alpha:
            break
        
    return best_so_far

def minimize(board, alpha, beta):
    global count
    count+=1
    best_so_far = MAX
    if terminal(board):
        return utility(board)
    
    move = float('inf')
    for action in actions(board):
        move = min(move, maximize(result(board, action), alpha, beta))
        best_so_far = min(best_so_far, move) 
        beta = min(beta, best_so_far) 

        if beta<=alpha: 
            break
    
    return best_so_far


def minimax(board):
    """
    Returns the optimal action for the current player on the board.
    """
    print(count)
    if terminal(board):
        return None
    
    elif player(board) == X:
        max = 0
        action_list = []
        for action in actions(board):
            action_list.append((minimize(result(board, action), MIN, MAX), action))
        
        max_act = (float('-inf'), None)

        for x in action_list:
            if x[0]>max_act[0]:
                max_act = (x[0], x[1])
        return max_act[1]
    
    elif player(board) == O:
        min = 0
        action_list = []
        for action in actions(board):
            action_list.append((maximize(result(board, action), MIN, MAX), action))
        min_act = (float('inf'), None)
        for x in action_list:
            if x[0]<min_act[0]:
                min_act = (x[0], x[1])
        return min_act[1]
    
