"""
    Author: Khushi Jirge

    Date: June 20, 2022

    Description: This game, Alien Attackers, is inspired by Space Invaders.
"""

import pygame
import AlienAttackersSprites
import random
import time

pygame.init()

def starting_screen(screen):
    """
    This function displays a starting menu with the start button and instructions.
    It accepts 1 parameter: the main screen.
    It returns True/False depending on whether the user closes the window.
    """

    # create a blue background entity
    background = pygame.Surface(screen.get_size())
    background = background.convert()
    background.fill((0, 0, 128))

    # create an object of the start button
    start_button = AlienAttackersSprites.StartButton(screen)
    Sprites = pygame.sprite.OrderedUpdates(start_button)

    # Font and text rendering for the Title and Instructions
    creepyFont = pygame.font.Font("Creepy font.ttf", 70)
    game_title = creepyFont.render("ALIEN   ATTACKERS!", True, (255, 255, 0))

    instructions = pygame.font.SysFont("Courier", 18)
    line1 = instructions.render("Kill all of the aliens on the top of the screen.", True, (255, 255, 0))
    line2 = instructions.render("Use left and right keys to move the cannon player.", True, (255, 255, 0))
    line3 = instructions.render("Use space bar to shoot a bullet. You only have 35 bullets.", True, (255, 255, 0))
    line4 = instructions.render("Watch out for the attackers' bullets.", True, (255, 255, 0))
    line5 = instructions.render("Shooting a randomly arriving special angel will award you.", True, (255, 255, 0))
    line6 = instructions.render("Enjoy!", True, (255, 255, 0))

    clock = pygame.time.Clock()
    keepGoing = True
    # Loop
    while keepGoing:
        # Time
        clock.tick(30)

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
                pygame.quit()
                return False
            if event.type == pygame.MOUSEBUTTONDOWN:
                # checks if the mouse hits the start button
                pos = pygame.mouse.get_pos()
                if start_button.rect.collidepoint(pos):
                    keepGoing = False
                    return True

        # blit the background, title, and instruction lines
        screen.blit(background, (0,0))
        screen.blit(game_title, (75,100))
        screen.blit(line1, (70,400))
        screen.blit(line2, (70,420))
        screen.blit(line3, (70,440))
        screen.blit(line4, (70,460))
        screen.blit(line5, (70,480))
        screen.blit(line6, (70,500))
        Sprites.clear(screen, background)
        Sprites.update()
        Sprites.draw(screen)
        pygame.display.flip()


def main():
    # Display
    screen = pygame.display.set_mode((800, 600))
    pygame.display.set_caption("Alien Attackers")
 	
    # background music
    pygame.mixer.music.load("background music.wav")
    pygame.mixer.music.set_volume(0.5)
    pygame.mixer.music.play(-1)

    # checks if user exited the starting game screen
    if not (starting_screen(screen)):
        return

    # Entities
    background = pygame.Surface(screen.get_size())
    background = background.convert()
    background.fill((255, 255, 255))
    screen.blit(background, (0,0))
 	
    # creates an object array of 18 attackers in 3 rows
    # 3 types of attackers: UFOs, Yoda, and Aliens
    attackers = []
    for x_pos in range(round(screen.get_width()/6), screen.get_width() + 1, round(screen.get_width()/6)):
        attackers.append(AlienAttackersSprites.UFOAttacker(screen, x_pos))
        attackers.append(AlienAttackersSprites.YodaAttacker(screen, x_pos))
        attackers.append(AlienAttackersSprites.AlienAttacker(screen, x_pos))
 	
    # music when the player hits the attacker
    attacker_dead = pygame.mixer.Sound("attacker explode.wav")
    attacker_dead.set_volume(1.0)

    # creates an array of 3 objects of the class Lives
    lives = []
    for i in range(0, 150, 50):
        lives.append(AlienAttackersSprites.Lives(screen, 650+i, 580))

    # the sound effect when player loses a life
    lose_life = pygame.mixer.Sound("lost life.wav")
    lose_life.set_volume(0.4)

    # creates an object of the player
    player = AlienAttackersSprites.Player(screen)

    # creates a label for the score
    score = 0
    score_label = AlienAttackersSprites.Score(str(score))
    # adds all objects to allSprites Group
    allSprites = pygame.sprite.OrderedUpdates(attackers, player, lives, score_label)

    # create arrays for bullets for player and attacker
    bullet_attacker = []
    bullet_player = []
    bullets_allowed = 35

    bullets_count_font = pygame.font.SysFont("Courier", 23)

    # sound effect of the bullets of the player and attackers
    player_shoot = pygame.mixer.Sound("player shoot.wav")
    player_shoot.set_volume(0.4)

    attacker_shoot = pygame.mixer.Sound("attacker shoot.wav")
    attacker_shoot.set_volume(0.4)

    try:
        # reads the score tracker file for the highest score from last game
        score_file = open("Score tracker.txt", 'r')
        text, high_score = score_file.readline().strip().split("=")
        high_score_font = pygame.font.SysFont("Courier", 20)
        high_score_label = high_score_font.render(text + " = " + high_score, True, (255, 0, 0))
        score_file.close()
    except (FileNotFoundError, UnboundLocalError) as error:
        print("Make sure the score tracker file is in the same directory.")
        return


    # text display for the end of the game
    end_text = pygame.font.SysFont("Courier", 70)
    win = end_text.render("YOU WIN!", True, (0, 0, 0))
    lose = end_text.render("YOU LOSE!", True, (0, 0, 0))

    player_lost = pygame.mixer.Sound("player lost.wav")
    player_lost.set_volume(1.0)

    player_win = pygame.mixer.Sound("player win.wav")
    player_win.set_volume(0.8)

    # creates an array of special aliens that actually help the player
    special = []
    specialSprites = pygame.sprite.OrderedUpdates(special)

    special_alien_hit = pygame.mixer.Sound("lucky alien hit.wav")
    special_alien_hit.set_volume(0.4)

    special_alien_coming = pygame.mixer.Sound("angel alien coming.wav")
    special_alien_coming.set_volume(0.8)

    # ACTION
 	
    # Assign
    clock = pygame.time.Clock()
    keepGoing = True
    pressed = ""
    result = ""
    # Loop
    while keepGoing:
        # Time
        clock.tick(30)
        screen.blit(background, (0,0))
        # Events

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    pressed = "left"
                if event.key == pygame.K_RIGHT:
                    pressed = "right"
                if event.key == pygame.K_SPACE:
                    pressed = "space"
                    player_shoot.play()
                    bullets_allowed -= 1
                    # gets player's position and instantiates a bullet object at that position
                    x_user, y_user = player.get_position()
                    bullet_player.append(AlienAttackersSprites.Bullet(screen, (x_user, y_user), -12, (0, 0, 0)))
                    # The next two lines clears all sprites and readds them to avoid blitting errors
                    allSprites.clear(screen, background)
                    allSprites = pygame.sprite.OrderedUpdates(attackers, bullet_attacker, bullet_player, player, lives, score_label)
            elif event.type == pygame.KEYUP:
                pressed = ""

        # keeps the player going respective direction
        if pressed == "left":
            player.go_left()
        elif pressed == "right":
            player.go_right()

        # checks if player lost
        if bullets_allowed == -1 or lives == []:
            pygame.mixer.music.stop()
            player_lost.play()
            result = "lose"
            keepGoing = False
            time.sleep(2.0)
            continue

        # checks if player won
        if attackers == []:
            pygame.mixer.music.stop()
            player_win.play()
            result = "win"
            keepGoing = False
            time.sleep(2.0)
            continue

        # 1/30 chance per frame that a random attacker will shoot a bullet
        if (random.randrange(30) == 0):
            attacker_shoot.play()
            # gets position of the attacker and instantiates a bullet object at that position
            x, y = attackers[random.randrange(len(attackers))].get_position()
            bullet_attacker.append(AlienAttackersSprites.Bullet(screen, (x, y), 8, (255, 0, 0)))
            # The next two lines clears all sprites and readds them to avoid blitting errors
            allSprites.clear(screen, background)
            allSprites = pygame.sprite.OrderedUpdates(attackers, bullet_attacker, bullet_player, player, lives, score_label)

        if bullet_attacker != []:
            attacker_x, attacker_y = bullet_attacker[len(bullet_attacker)-1].get_position()
            if attacker_y > screen.get_height():
                bullet_attacker.pop(len(bullet_attacker)-1)

        if bullet_player != []:
            player_x, player_y = bullet_player[len(bullet_player)-1].get_position()
            if player_y < 0:
                bullet_player.pop(len(bullet_player)-1)

        # 1/400 chance per frame that the special angel will appear
        # makes sure that angels only appear 1 at a time
        if special != []:
            x_angel, y_angel = special[len(special)-1].get_position()
            if (random.randrange(400) == 0):
                if not x_angel >= 0:
                    special_alien_coming.play()
                    special.append(AlienAttackersSprites.SpecialAttacker(screen))
                    # The next two lines clears all sprites and readds them to avoid blitting errors
                    specialSprites.clear(screen, background)
                    specialSprites = pygame.sprite.OrderedUpdates(special)

            if x_angel < 0:
                special.pop(len(special)-1)

        else:
            if (random.randrange(400) == 0):
                special_alien_coming.play()
                special.append(AlienAttackersSprites.SpecialAttacker(screen))
                # The next two lines clears all sprites and readds them to avoid blitting errors
                specialSprites.clear(screen, background)
                specialSprites = pygame.sprite.OrderedUpdates(special)

        # checks if the player's bullets hit the attacker or the special angel
        for bullet in bullet_player:
            for attacker in attackers:
                if attacker.rect.colliderect(bullet.rect):
                    # deletes the attcker object if the bullet hits it
                    attacker_dead.play()
                    attackers.remove(attacker)
                    # The next two lines clears all sprites and readds them to avoid blitting errors
                    allSprites.clear(screen, background)
                    allSprites = pygame.sprite.OrderedUpdates(attackers, bullet_attacker, bullet_player, player, lives, score_label)
                    score += attacker.get_points()
                    score_label.setText(str(score))

            for special_alien in special:
                if special_alien.rect.colliderect(bullet.rect):
                    # if bullet hits the angel, it randomly choses 1 out of the 3 awards
                    special_alien_hit.play()
                    x = random.randrange(3)
                    if x == 0:
                        score += 100
                        text = end_text.render("+100 Score", True, (0, 0, 0))
                    elif x == 1:
                        lives.insert(0, AlienAttackersSprites.Lives(screen, lives[0].get_xpos() - 50, 580))
                        text = end_text.render("+1 Life", True, (0, 0, 0))
                    elif x == 2:
                        bullets_allowed += 5
                        text = end_text.render("+5 Bullets", True, (0, 0, 0))

                    screen.blit(text, (250, 250))
                    pygame.display.flip()
                    time.sleep(1.0)
                    special.remove(special_alien)
                    # The next two lines clears all sprites and readds them to avoid blitting errors
                    specialSprites.clear(screen, background)
                    specialSprites = pygame.sprite.OrderedUpdates(special)

        # checks if the attackers' bullet hit the player and they lose a life
        for bullet in bullet_attacker:
            if player.rect.colliderect(bullet.rect):
                lose_life.play()
                bullet_attacker.remove(bullet)
                lives.pop(0)
                allSprites.clear(screen, background)
                allSprites = pygame.sprite.OrderedUpdates(attackers, bullet_attacker, bullet_player, player, lives, score_label)

        bullets_label = bullets_count_font.render("Bullets left = " + str(bullets_allowed), True, (0, 0, 255))

        # Refresh screen
        allSprites.clear(screen, background)
        specialSprites.clear(screen, background)

        # The next lines call the update() method for any sprites in the 2 groups.
        allSprites.update()
        specialSprites.update()

        allSprites.draw(screen)
        specialSprites.draw(screen)

        screen.blit(high_score_label, (10, 550))
        screen.blit(bullets_label, (550, 10))


        pygame.display.flip()


    # displays final message depending on whether the player won or lost
    if result == "win":
        screen.blit(win, (220, 250))
        pygame.display.flip()

        keepGoing = True
        while keepGoing == True:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    keepGoing = False
    elif result == "lose":
        screen.blit(lose, (220, 250))
        pygame.display.flip()

        keepGoing = True
        while keepGoing == True:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    keepGoing = False
    else:
        pygame.quit()

    # updates final score to the high score file
    high_score = int(high_score)
    if high_score < score:
        high_score = score

    new_score_file = open("Score tracker.txt", "w")
    new_score_file.write("High score=" + str(high_score) + "\n")
    new_score_file.close()

# Call the main function
main()
