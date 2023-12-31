"""
    Author: Khushi Jirge

    Date: June 20, 2022

    Description: This is the Sprites class.
"""
import pygame

# Parent class for all types of attackers
class Attacker(pygame.sprite.Sprite):
    def __init__(self, screen, picture, start_pos, direct, points):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)
     	
        # Keep track of the screen so we can call get_width()
        self.window = screen
     	
        self.image = pygame.image.load(picture)
        self.image = self.image.convert()

        self.rect = self.image.get_rect()
        self.rect.centerx = start_pos[0]
        self.rect.centery = start_pos[1]
        self.left = self.rect.left
        self.right = self.rect.right
        self.direction = direct

        self.points = points

    def get_position(self):
        '''This method returns the x and y position'''
        return self.rect.centerx, self.rect.centery

    def get_points(self):
        '''This method returns the number of points'''
        return self.points

    def update(self):
        # checks if the sprite hits the window's edge
        # if it does, it appears on the opposite side
        self.rect.centerx += self.direction
        if (self.rect.centerx < 0):
            self.rect.centerx = self.window.get_width()
        elif (self.rect.centerx > self.window.get_width()):
            self.rect.centerx = 0
        elif (self.rect.centery < 0):
            self.rect.centery = self.window.get_height()
        elif (self.rect.centery > self.window.get_height()):
            self.rect.centery = 0

class UFOAttacker(Attacker):
    def __init__(self, screen, x_pos):
        # Call the parent __init__() methods
        pygame.sprite.Sprite.__init__(self)
        Attacker.__init__(self, screen, "UFO.jpg", (x_pos, 80), -3, 30)

class YodaAttacker(Attacker):
    def __init__(self, screen, x_pos):
        # Call the parent __init__() methods
        pygame.sprite.Sprite.__init__(self)
        Attacker.__init__(self, screen, "yoda.jpeg", (x_pos, 130), 5, 20)

class AlienAttacker(Attacker):
    def __init__(self, screen, x_pos):
        # Call the parent __init__() methods
        pygame.sprite.Sprite.__init__(self)
        Attacker.__init__(self, screen, "alien.jpg", (x_pos, 180), -7, 10)

class SpecialAttacker(Attacker):
    def __init__(self, screen):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)
        self.window = screen
     	
        self.image = pygame.image.load("special alien.jpg")
        self.image = self.image.convert()

        self.rect = self.image.get_rect()
        self.rect.centerx = self.window.get_width()
        self.rect.centery = 30

    def update(self):
        '''The special alien angel can only move in one direction in x-value'''
        self.rect.centerx -= 5


class Bullet(pygame.sprite.Sprite):
    def __init__(self, screen, start_pos, direct, color):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)
     	
        # Keep track of the screen so we can call get_width()
        self.window = screen

        self.image = pygame.Surface((5, 15))
        self.image = self.image.convert()
        self.image.fill(color)

        self.rect = self.image.get_rect()

        self.rect.centerx = start_pos[0]
        self.rect.centery = start_pos[1]
        self.direction = direct

    def get_position(self):
        '''This method returns the x and y position'''
        return self.rect.centerx, self.rect.centery

    def update(self):
        self.rect.centery += self.direction

class Player(pygame.sprite.Sprite):
    def __init__(self, screen):
        # call parent __init__() method
        pygame.sprite.Sprite.__init__(self)

        self.window = screen

        self.image = pygame.image.load("player.png")
        self.image = self.image.convert()

        self.rect = self.image.get_rect()
        self.rect.centerx = self.window.get_width() / 2
        self.rect.centery = self.window.get_height() - 70
        self.direction = 10

    def go_left(self):
        '''This method changes the x value of the position to move it left'''
        self.rect.centerx -= self.direction

    def go_right(self):
        '''This method changes the x value of the position to move it right'''
        self.rect.centerx += self.direction

    def get_position(self):
        '''This method returns the x and y position'''
        return self.rect.centerx, self.rect.centery

    def update(self):
        # checks if the player hits the window's edge
        # if it does, it appears on the opposite side
        if (self.rect.centerx < 0):
            self.rect.centerx = self.window.get_width()
        elif (self.rect.centerx > self.window.get_width()):
            self.rect.centerx = 0
        elif (self.rect.centery < 0):
            self.rect.centery = self.window.get_height()
        elif (self.rect.centery > self.window.get_height()):
            self.rect.centery = 0

class Lives(pygame.sprite.Sprite):
    def __init__(self, screen, x, y):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)

        self.window = screen
        self.image = pygame.image.load("lives.png")
        self.image = self.image.convert()
        self.rect = self.image.get_rect()
        self.rect.centerx = x
        self.rect.centery = y

    def get_xpos(self):
        '''This method returns the x position'''
        return self.rect.centerx

class Score(pygame.sprite.Sprite):
    def __init__(self, score):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)
        self.font = pygame.font.SysFont("Courier", 20)
        self.text = "Score = " + score

    def setText(self, score):
        '''Mutator for text to be displayed on the label.'''
        self.text = "Score = " + score

    def update(self):
        '''Render and center the label text on each Refresh.'''
        self.image = self.font.render(self.text, 1, (255, 0, 0))
        self.rect = self.image.get_rect()
        self.rect.left = 10
        self.rect.top = 570

class StartButton(pygame.sprite.Sprite):
    def __init__(self, screen):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)
        self.window = screen

        self.image = pygame.image.load("start button.png")
        self.image = self.image.convert()
        self.rect = self.image.get_rect()
        self.rect.centerx = self.window.get_width() / 2
        self.rect.centery = self.window.get_height() / 2
