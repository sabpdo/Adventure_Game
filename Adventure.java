import java.util.ArrayList;
import java.util.Scanner;

public class Adventure implements AdventureConstants 
{
    private static boolean gameOver = false;
    private static String command = "";
    private static String phrase = "";
    private static ArrayList<String> backpack = new ArrayList<String>();
    private static int score = 0;
    private static int currentArea = ENTRANCE; //Need to initialize this one to your constant name of your starting area
    private static Area[] world = new Area[25];//Initialize this to be the size of your world
    
    private static boolean useVacuumCleaner=false;
    private static boolean useStrobulb=false;
    private static boolean wearingNightGoggles = false;

    static
    {
        System.out.println("             ('-. .-.               .-')    .-') _         "); 
        System.out.println("            ( OO )  /              ( OO ). (  OO) )         ");
        System.out.println("  ,----.    ,--. ,--. .-'),-----. (_)---\\_)/     '._        ");
        System.out.println(" '  .-./-') |  | |  |( OO'  .-.  '/    _ | |'--...__)       ");
        System.out.println(" |  |_( O- )|   .|  |/   |  | |  |\\  :` `. '--.  .--'       ");
        System.out.println(" |  | .--, \\|       |\\_) |  |\\|  | '..`''.)   |  |          ");
        System.out.println("(|  | '. (_/|  .-.  |  \\ |  | |  |.-._)   \\   |  |          ");
        System.out.println(" |  '--'  | |  | |  |   `'  '-'  '\\       /   |  |          ");
        System.out.println("  `------'  `--' `--'     `-----'  `-----'    `--'          ");
        System.out.println(".-. .-')                 .-')    .-') _     ('-.  _  .-')   ");
        System.out.println("\\  ( OO )               ( OO ). (  OO) )  _(  OO)( \\( -O )  ");
        System.out.println(" ;-----.\\  ,--. ,--.   (_)---\\_)/     '._(,------.,------.  ");
        System.out.println(" | .-.  |  |  | |  |   /    _ | |'--...__)|  .---'|   /`. ' ");
        System.out.println(" | '-' /_) |  | | .-') \\  :` `. '--.  .--'|  |    |  /  | | ");
        System.out.println(" | .-. `.  |  |_|( OO ) '..`''.)   |  |  (|  '--. |  |_.' | ");
        System.out.println(" | |  \\  | |  | | `-' /.-._)   \\   |  |   |  .--' |  .  '.' ");
        System.out.println(" | '--'  /('  '-'(_.-' \\       /   |  |   |  `---.|  |\\  \\  ");
        System.out.println(" `------'   `-----'     `-----'    `--'   `------'`--' '--'");
        System.out.println();
        System.out.println("Welcome Ghost Buster. You are a renowned ghost hunter "
            + "\nwho is inside a haunted mansion.The mansion has been abandoned since Elizabeth, "
            + "\na duchess, was murdered at her birthday party. Ever since her murder, she has "
            + "\nhaunted the mansion, seeking revenge on the her murderer and killing those "
            + "\nwho cross her path. Enlistened by a descendant of Elizabeth's sister, "
            + "\nyour job is gather your ghost hunting materials (a strobulb and a vacuum cleaner), find"
            + "\nthe ghost, and remove the supernatural threat, making the mansion sellable and"
            + "\ninhabitable once again.");
        System.out.println();
        System.out.println("To play this game, type short phrases into the command line "
            + "\nbelow. For example, to pick up an object, type the word "
            + "\n\"get\" and the name of the object you would like to pick up. Direct me with commands "
            + "\nsuch as north to move north. Type \"inv\" to access your"
            + "\ninventory/backpack and \"quit\" to end the game at anytime.");
        //In the static block, you need to attach Area objects to every spot in the world array
        for (int spot = 0; spot<=24; spot++)
        {
            world[spot] = new Area();
        }
    }    

    public static void main(String[] args) 
    { 
        initializeWorld();  

        while (!gameOver) 
        { 
            world[currentArea].displayArea();
            getUserInput(); 
            parseUserInput(); 
        } 

        displayFarewellMessageAndScore(); 
    }

    private static void initializeWorld()
    {
        //This method needs to set the exits for the areas so your world map works
        //These exits are the *initial* state of the world (before doors are unlocked and such)
        //This method also needs to set the description of each area
        //
        //Finally, this method also needs to put the items that are *initially* laying on the 
        //ground in areas around your world.
        world[ENTRANCE].setDescription("You enter a grand entrance hallway, " 
            + "\nadorned with glittering marble floors and floral wallpaper. "
            + "\nThe front of the room is headed by two creaky wooden doors, " 
            + "\nand the mirror on the wall seems as if it could have come " 
            + "\nstraight from a 16th century palace. Below the mirror is a "
            + "\ncabinet with a pair of green-tinted goggles laying on top that " 
            + "\nseem to be glowing. However, the chilling beauty of the room is " 
            + "\namplified by the eerie and cold breeze in the hall that sends " 
            + "\nshivers down your back and the soft moonlight that flows from " 
            + "\nthe glass doors on both sides of the room."); 
        world[PATIO].setDescription("As you enter the patio, the bright moon illuminates \n" 
            + "the area like a halo. You see granite — no marble— railings that \n"
            + "shields you from leaving the estate and the dark forest that surrounds \n"
            + "the mansion. The centerpiece of the patio is the cracked table set. Once \n"
            + "a space that friends and guests bathed in the sun while sipping lemonade, \n"
            + "the cracked marble benches and tables seem to be a remnant of the past.");
        world[GARDEN].setDescription("The garden that was once groomed to perfection now lays "
            + "\novergrown. Ivy climbs the walls of the house and the various angel statues. "
            + "\nIn the center of the garden lies a large statue of a woman, like a tribute "
            + "\nto a goddess, that is cracked and covered in vines. Amidst the overgrown hedges, "
            + "\nthere is a rocky path that has small cracks within, with small daisies sprouting "
            + "\nout of the cracks. ");
        world[FOYER].setDescription("A large room with ceilings as high as infinity greets you. "
            + "\nYou could imagine a scene of friends and family greeting each other here with "
            + "\nhugs after entering from the entrance, but now the foyer is empty, bare, " 
            + "\nand devoid of human life. The only noticeable items are the scattered remains of" 
            + "\nsome flowers on the ground.");
         world[WEST_STAIRWAY].setDescription("The stairway extends ahead of you; you can "
            + "\nimagine the grand entrance one could make down these stairs like in a movie "
            + "\ndown to the foyer. You must watch your step as some of the steps are cracked "
            + "\nand worn-down. Pieces of broken steps and marble lay across the steps, "
            + "\nbut there are some shaky rails to assist you in your flight. You notice "
            + "\nthe temperature noticeably drop in this area as you shiver and the"
            + "\nspots of dried liquid—blood?—on the stairs. ");
         world[NORTH_STAIRWAY].setDescription("The stairway extends ahead of you, "
            + "\ncurving ahead to a doorway to the Attic; you can imagine the grand "
            + "\nentrance one could make down these stairs like in a movie. "
            + "\nYou must watch your step as some of the steps are cracked and worn-down. "
            + "\nPieces of broken steps and marble lay across the steps, but there "
            + "\nare some shaky rails to assist you in your flight. ");
         world[ATTIC].setDescription("To your surprise, the attic is empty and devoid of "
            + "\nany boxes or items. All that resides in the attic is a layer of dust" 
            + "\nthat coats the wooden floor. The floor is full of perilous holes. "
            + "\nSomewhere in the room, you hear a rat squeak and run across.  "
            + "\nHowever, most noticeable is the freezing temperatures in the "
            + "\nroom and the cold spots. You hear the voice of the ghost "
            + "\ntaunting you from all directions of the room, but you cannot see "
            + "\nthe ghost. "
            + "\nHint on fighting the ghost: Can you see the ghost? Can you blind the ghost? "
            + "\nDo you have a way to destroy the ghost? Make sure you have the items to do so.");
         world[LIBRARY].setDescription("The library encompasses high ceilings with bookshelves "
            + "\nthat seem to extend to the top. However, the bookshelves "
            + "\nare sparsely populated with books. Despite the layer of dust "
            + "\nthat coats the books, the books seem to be almost new—like nobody "
            + "\nhas cracked them open ever. Splotchy dark red carpet "
            + "\nlies under your feet—an interesting dye job—and candlestick "
            + "\nholders lay on the walls. ");
         world[BILLIARD_ROOM].setDescription("A green pool table lays in the middle of the"
            + "\nbilliard room with brightly colored balls in a triangle "
            + "\nin the middle. Pool sticks are pinned to the walls, ready to be used, "
            + "\nand you can see a Pacman arcade in the corner that no longer can power on. ");
         world[CONSERVATORY].setDescription(" Adorned by glass on all sides of the room, the "
            + "\nconservatory is filled with dead plants which are showered with moonlight. "
            + "\nBlack and wilted leaves and petals litter the floor. Once a safe "
            + "\nhaven, the conservatory is now an abandoned room. Sitting "
            + "\nalone in the middle of the room is a table with a single sheet on "
            + "\n it. The worn-down and watered sheet seems to have music notes on it."
            + "\nYou can barely make out the title on the sheet from the water "
            + "\nstains: “Picture to Burn.”");
         world[WEST_HALLWAY].setDescription("As you walk in the hallway, the dusty "
            + "\npatterned carpet flattens under your feet. To the north "
            + "\nof the room, there is a large locked wooden door. The eerie hallway"
            + "\nstands mostly bare with white walls and minimal decorations.");
         world[MASTER_BEDROOM].setDescription("In the middle of the master bedroom looms the large bed, "
            + "\nlike that for a king. However, it is covered with a thin white "
            + "\nveil which has accumulated decades of dust. Besides the bed, the room " 
            + "\nremains bare besides for the chipped mirror on the wall and a painting of a woman. ");
         world[NORTH_HALLWAY].setDescription("As you walk in the hallway, the dusty "
            + "\npatterned carpet flattens under your feet. The eerie hallway "
            + "\nstands mostly bare with white walls and minimal decorations."); 
         world[STUDY].setDescription("You can imagine people once relaxing or reading "
            + "\na book by the fireplace in the cozy armchair in the study, "
            + "\nnow covered by a thin white veil. The fireplace is boarded up with "
            + "\ntwo wooden planks and filled with old, black ashes. The study table, "
            + "\ncovered in various unopened books, is also covered in a layer of dust "
            + "\nand a cloth like that of a bride’s dress. ");
         world[MASTER_BATHROOM].setDescription(" With marble countertops, the master bathroom features "
            + "\ncrystal facilities like that of a palace. However, none of the "
            + "\nfaucets do not work as they are rusted over with a blue substance. "
            + "\nCobwebs hang in the dark corners of the room, and you try "
            + "\nto avoid the spiders and insects that roam the ground. The bathtub "
            + "\nis filled with shards of glass and marble and it is obvious that the "
            + "\nbathroom has not been used in a long time. ");
         world[CLOSET].setDescription("The closet, very dark to see through, is empty except "
            + "\nfor one type of item: multiple flashlights that emit a powerful "
            + "\nlight and seems to have supernatural abilities." 
            + "\nThe racks where there is supposed to be clothing are cleared and "
            + "\nbare, and the ground has become a habitat for spiders and their cobwebs. ");
         world[LIVING_ROOM].setDescription("Two large red-pink sofa chairs center around "
            + "\nthe fireplace in the living room. One can imagine families once "
            + "\ngathering together, laughing, and drinking tea together. "
            + "\nNow all that remains are the intermixed combination of dust "
            + "\nand velvet of the chairs, broken teacups, a dead fireplace, "
            + "\nand the feeling of sadness, anger, and chills in the air. ");
         world[LOUNGE].setDescription("With coffee-stains on the regal chairs that gather "
            + "\ntogether like people, the lounge brings a sort of comfort. "
            + "\nYou can imagine the chatter of guests after dinner that once occurred "
            + "\nhere and can see cigarette butts on the ground. A musty cigar "
            + "\nsmells lingers in the air, perhaps entrapped in the floral "
            + "\nrugs that match the floral wallpaper. ");
         world[DINING_ROOM].setDescription("As you enter the dining room, you notice the large "
            + "\nwooden table that extends the whole length of the room. The table "
            + "\nseems to be set for a party with a set of silver utensils and a "
            + "\nceramic plate for each place. But the layer of dust suggests that "
            + "\nthere has not been a party at this mansion in quite some time. "
            + "\nOver the table hangs a beautiful chandelier that is decorated with webs"
            + "and red ribbons. ");
         world[KITCHEN].setDescription(" Knives glisten from the walls of the kitchen."
            + "\nVarious cooking materials scatter the room and "
            + "\ntabletops next to the cooking devices. Once a cook’s "
            + "\ndream, the kitchen is now outdated and has malfunctioning "
            + "\nappliances. The date on the timer in the corner of the room is \n"
            + "\nfive years and two days off the real date, and a putrid "
            + "\nsmell emits from the fridge, probably from "
            + "\nthe bones of the chicken that were never thrown away. ");
         world[GUEST_BEDROOM].setDescription("In the middle of the guest bedroom "
            + "\nis a medium size bed. However, it is covered with a "
            + "\nthin white veil which has accumulated decades of dust. "
            + "\nBesides the bed, the room remains bare besides for "
            + "\nsome empty cabinets and a letter wishing happy "
            + "\nbirthday to Elizabeth. ");
         world[PIANO_ROOM].setDescription("The grand piano is the centerpiece of the "
            + "\npiano room, almost taking up the entire room. The sheet music "
            + "\nstand is dusty. Empty chairs that would typically be filled "
            + "\nwith admirers surround the piano, and you can imagine the "
            + "\nround of applause the piano player would receive. A dark, "
            + "\ndead chandelier with diamond-like crystals"
            + "\nhovers over the piano.");
         world[BALLROOM].setDescription("You can imagine the dancers waltzing in the" 
            + "\nmagnificent and expansive ballroom over the black and "
            + "\nwhite tiles and the belle of the ball making her grand"
            + "\nentrance. It seems as the room was decorated for a "
            + "\ncelebration, perhaps a party, as red ribbons adorn the glass"
            + "\nwindows as well as dead red roses that have wilted. "); 
         world[EAST_STAIRWAY].setDescription("The stairway continues ahead of you. \n"
            + "Many of the steps you are walking on are  made of cracked \n"
            + "pieces of marble, and there is a freezing cold metal rail for support. \n"
            + "Stone brick walls enclose you on both sides, and a large, vicious, scary dog \n"
            + "guards the entrance to the room north of the stairway. ");
         world[BASEMENT].setDescription("The darkly lit room prevents you from seeing any of the objects \n"
            + "in the room or where you are.");

         world[ENTRANCE].setNorth(FOYER);
         world[ENTRANCE].setEast(PATIO);
         world[ENTRANCE].setWest(GARDEN);

         world[PATIO].setWest(ENTRANCE);

         world[GARDEN].setEast(ENTRANCE);
            
         world[FOYER].setNorthwest(WEST_STAIRWAY);
         world[FOYER].setNortheast(EAST_STAIRWAY);
         world[FOYER].setWest(LIVING_ROOM);
         world[FOYER].setEast(LOUNGE);
         world[FOYER].setSouth(ENTRANCE);
         
         world[WEST_STAIRWAY].setNorth(NORTH_STAIRWAY);
         world[WEST_STAIRWAY].setSouth(FOYER);
         
         world[NORTH_STAIRWAY].setSouthwest(WEST_STAIRWAY);
         world[NORTH_STAIRWAY].setSoutheast(ATTIC);
         
         world[ATTIC].setNorth(NORTH_STAIRWAY);
         
         world[LIBRARY].setNorth(LOUNGE);
         
         world[BILLIARD_ROOM].setNorth(LIVING_ROOM);
         
         world[CONSERVATORY].setNorth(WEST_HALLWAY);
         
         world[WEST_HALLWAY].setSouth(CONSERVATORY);
         world[WEST_HALLWAY].setEast(LIVING_ROOM);
         
         world[MASTER_BEDROOM].setNortheast(NORTH_HALLWAY);
         world[MASTER_BEDROOM].setSoutheast(MASTER_BEDROOM);
         
         world[NORTH_HALLWAY].setWest(MASTER_BEDROOM);
         world[NORTH_HALLWAY].setEast(STUDY);
         world[NORTH_HALLWAY].setSoutheast(CLOSET);
         
         world[STUDY].setWest(NORTH_HALLWAY);
         world[STUDY].setSouth(CLOSET);
         
         world[MASTER_BATHROOM].setWest(MASTER_BEDROOM);
         
         world[CLOSET].setNorth(STUDY);
         world[CLOSET].setNorthwest(NORTH_HALLWAY);
         
         world[LIVING_ROOM].setEast(FOYER);
         world[LIVING_ROOM].setWest(WEST_HALLWAY);
         world[LIVING_ROOM].setSouth(BILLIARD_ROOM);
         
         world[LOUNGE].setWest(FOYER);
         world[LOUNGE].setEast(PIANO_ROOM);
         world[LOUNGE].setNorth(DINING_ROOM);
         world[LOUNGE].setSouth(LIBRARY);
         
         world[DINING_ROOM].setNorth(KITCHEN);
         world[DINING_ROOM].setSouth(LOUNGE);
         
         world[KITCHEN].setSouth(DINING_ROOM);
         
         world[GUEST_BEDROOM].setSouthwest(LOUNGE);
         
         world[PIANO_ROOM].setSouth(BALLROOM);
         world[PIANO_ROOM].setWest(LOUNGE);
         
         world[BALLROOM].setNorth(PIANO_ROOM);
         
         world[EAST_STAIRWAY].setSouth(FOYER);
         
         world[FOYER].addItem("wooden key");
         world[CONSERVATORY].addItem("piano sheet music");
         world[KITCHEN].addItem("bone");
         world[ENTRANCE].addItem("night vision goggles");
         world[CLOSET].addItem("strobulb");
    }   

    private static void getUserInput()
    {
        Scanner userInput = new Scanner(System.in);
        String input = "";

        System.out.print("--> ");
        input = userInput.nextLine();
        input = StringParser.formatString(input);
        command = StringParser.getCommand(input);
        phrase = StringParser.getPhrase(input);
    }

    private static void parseUserInput()
    {
        Scanner userInput = new Scanner(System.in);
        if (command.equals("q") || command.equals("quit"))
            gameOver = true;

        if (command.equals("i") || command.equals("inv") || command.equals("inventory"))
        {
            if (backpack.isEmpty())
                System.out.println("Your backpack is empty.");
            else
            {
                System.out.println("Your backpack contains:");
                for (String item: backpack)
                    System.out.println(item);
            }
        }

        if (command.equals("score"))
        {
            System.out.println("You currently have " + score + " points.");
        }
        
        if (command.equals("north") || command.equals("n"))
        {
            move("north");
        }
        if (command.equals("south") || command.equals("s"))
        {
            move("south");
        }
        if (command.equals("east") || command.equals("e"))
        {
            move("east");
        }    
        if (command.equals("west") || command.equals("w"))
        {
            move("west");
        }
        if (command.equals("southeast") || command.equals("se"))
        {
            move("southeast");
        }
        if (command.equals("southwest") || command.equals("sw"))
        {
            move("southwest");
        }
        if (command.equals("northwest") || command.equals("nw"))
        {
            move("northwest");
        }
        if (command.equals("northeast") || command.equals("ne"))
        {
            move("northeast");
        }
        if (command.equals("up") || command.equals("u"))
        {
            move("up");
        }
        if (command.equals("down") || command.equals("d"))
        {
            move("down");
        }
        
        if (command.equals("go") || command.equals("walk") || command.equals("run") ||
            command.equals("move") || command.equals("proceed") || command.equals("travel") ||
            command.equals ("journey") || command.equals("depart") || command.equals("set off")||
            command.equals("sprint") || command.equals("stroll") || command.equals("saunter") ||
            command.equals("stride") || command.equals("march") || command.equals("wander"))
        {
            if (phrase.equals("north") || phrase.equals("n"))
            {
                move("north");
            }
            if (phrase.equals("south") || phrase.equals("s"))
            {
                move("south");
            }
            if (phrase.equals("east") || phrase.equals("e"))
            {
                move("east");
            }    
            if (phrase.equals("west") || phrase.equals("w"))
            {
                move("west");
            }
            if (phrase.equals("southeast") || phrase.equals("se"))
            {
                move("southeast");
            }
            if (phrase.equals("southwest") || phrase.equals("sw"))
            {
                move("southwest");
            }
            if (phrase.equals("northwest") || phrase.equals("nw"))
            {
                move("northwest");
            }
            if (phrase.equals("northeast") || phrase.equals("ne"))
            {
                move("northeast");
            }
            if (phrase.equals("up") || phrase.equals("u"))
            {
                move("up");
            }
            if (phrase.equals("down") || phrase.equals("d"))
            {
                move("down");
            }
        }
        
        if (command.equals("take") || command.equals("get") || command.equals("grab") ||
            command.equals("clutch") || command.equals("clasp") || command.equals("grip") ||
            command.equals("grasp") || command.equals("acquire") || command.equals("obtain") ||
            command.equals("seize") || command.equals("snatch"))
        {
            if (world[currentArea].removeItem(phrase))
            {   //add the item to your backpack
                backpack.add(phrase);
                System.out.println("You now have the " + phrase + ".");
                if (phrase.equals("strobulb"))
                {
                    System.out.println("The strobulb is a essential item in defeating ghostts"
                        + "\nThe strobulb is a flashlight whicih creates a strong blast of light"
                        + "\nthat can temporarily stun and let you see ghosts.");
                }
                else if (phrase.equals("vacuum"))
                {
                    System.out.println("A vacuum may be handy later in helping you defeat"
                        +"\nthe ghost. Think of how a vacuum may be used."); 
                }
                
            }
            else 
            {
                System.out.println("Sorry, there is no such item in your current area.");
            }
        }
        
        
        if (command.equals("drop") || command.equals("unhand") || command.equals("release") ||
            command.equals("relinquish") || command.equals("deposit") || command.equals("leave") ||
            command.equals("lay"))
        {
            if (backpack.indexOf(phrase)>=0)
            {   //remove the item from your backpack
                backpack.remove(backpack.indexOf(phrase));
                world[currentArea].addItem(phrase);
                System.out.println ("You now have now dropped the " + phrase + ".");
            }
            else 
            {
                System.out.println("Sorry, there is no such item in your backpack.");
            }
        }
        
        if (command.equals("unlock"))
        {
            if (phrase.equals("door") && currentArea == WEST_HALLWAY)
            { 
                System.out.println("What do you want to unlock the door with?");
                getUserInput();
                if (command.equals("wooden") && (phrase.equals("key")))
                {
                    if (backpack.indexOf("wooden key")>=0)
                    {   
                        //remove the item from your backpack
                        backpack.remove(backpack.indexOf("wooden key"));
                        world[WEST_HALLWAY].setDescription("As you walk in the hallway, the dusty patterned carpet flattens \n"
                        + "under your feet. To the north of the room, there is a large wooden door swung open, allowing \n" 
                        + "you to peak into the next room. The eerie hallway stands mostly bare with white walls and minimal \n"
                        + "decorations. ");
                        world[WEST_HALLWAY].setNorth(MASTER_BEDROOM);
                        world[MASTER_BEDROOM].setSouth(WEST_HALLWAY);
                        score+=5;
                        System.out.println("You have now unlocked the door with the wooden key.");
                    }
                    else 
                    {
                        System.out.println("Sorry, there is no wooden key in your backpack.");
                    }
                }
                if (command.equals("key"))
                {
                    System.out.println("What key do you want to unlock the door with?");
                    getUserInput();
                    
                    if (command.equals("wooden"))
                    {
                        if (backpack.indexOf("wooden key")>=0)
                        {   
                            //remove the item from your backpack
                            backpack.remove(backpack.indexOf("wooden key"));
                            world[WEST_HALLWAY].setDescription("As you walk in the hallway, the dusty patterned carpet flattens \n"
                                + "under your feet. To the north of the room, there is a large wooden door swung open, allowing \n" 
                                + "you to peak into the next room. The eerie hallway stands mostly bare with white walls and minimal \n"
                                + "decorations. ");
                            world[WEST_HALLWAY].setNorth(MASTER_BEDROOM);
                            world[MASTER_BEDROOM].setSouth(WEST_HALLWAY);
                            score+=5;
                            System.out.println("You have now unlocked the door with the wooden key.");
                        }
                        else 
                        {
                            System.out.println("Sorry, there is no wooden key in your backpack.");
                        }
                    }
                    else if (command.equals("wooden") && phrase.equals("key"))
                    {
                        if (backpack.indexOf("wooden key")>=0)
                        {   
                            //remove the item from your backpack
                            backpack.remove(backpack.indexOf("wooden key"));
                            world[WEST_HALLWAY].setDescription("As you walk in the hallway, the dusty patterned carpet flattens \n"
                                + "under your feet. To the north of the room, there is a large wooden door swung open, allowing \n" 
                                + "you to peak into the next room. The eerie hallway stands mostly bare with white walls and minimal \n"
                                + "decorations. ");
                            world[WEST_HALLWAY].setNorth(MASTER_BEDROOM);
                            world[MASTER_BEDROOM].setSouth(WEST_HALLWAY);
                            score+=5;
                            System.out.println("You have now unlocked the door with the wooden key.");
                        }
                        else 
                        {
                            System.out.println("Sorry, there is no wooden key in your backpack.");
                        }
                    }
                    else
                    {
                        System.out.println("Sorry, a " + command + "key does not exist in this game.");
                    }
                }
            }
            else if (phrase.equals("door with wooden key") && currentArea == WEST_HALLWAY) 
            { 
                // Unlock door
                if (backpack.indexOf("wooden key")>=0)
                {   //remove the item from your backpack
                    backpack.remove(backpack.indexOf("wooden key"));
                    world[WEST_HALLWAY].setDescription("As you walk in the hallway, the dusty patterned carpet flattens \n"
                        + "under your feet. To the north of the room, there is a large wooden door swung open, allowing \n" 
                        + "you to peak into the next room. The eerie hallway stands mostly bare with white walls and minimal \n"
                        + "decorations. ");
                    world[WEST_HALLWAY].setNorth(MASTER_BEDROOM);
                    world[MASTER_BEDROOM].setSouth(WEST_HALLWAY);
                    score+=5;
                    System.out.println("You have now unlocked the door with the wooden key.");
                }
                else 
                {
                    System.out.println("Sorry, there is no wooden key in your backpack.");
                }
            }
            else if (currentArea != WEST_HALLWAY)
            {
                System.out.println("I don't see anything to unlock here!");
            } 
        }
        
        if (command.equals("play"))
        {
            if (phrase.equals("piano") && currentArea == PIANO_ROOM)
            { 
                System.out.println("What in your inventory will you be using to play the piano?");    
                getUserInput();
                if (command.equals("piano") && phrase.equals("sheet music") || command.equals("sheet") && phrase.equals("music"))
                {
                    if (backpack.indexOf("piano sheet music")>=0)
                    {   
                        //remove the item from your backpack
                        backpack.remove(backpack.indexOf("piano sheet music"));
                        world[PIANO_ROOM].setDescription("The grand piano is the centerpiece of the piano room," 
                            + "\nalmost taking up the entire room. A bright, illuminated "
                            + "\nchandelier with diamond-like crystals hovers over"
                            + "\nand illuminates the piano. Empty chairs that "
                            + "\nwould typically be filled with admirers surround the piano, "
                            + "and you can imagine the round of applause the piano player would receive. ");
                        System.out.println("You hear the sound of the beautiful piano music ring through the empty mansion. "
                            + "\nSoon, after a brief period of deadly silence, you hear the melody repeat "
                            + "\nback to you, coming from attic on the north side of the mansion, giving you a hint where "
                            + "\nthe ghost is. ");
                        world[NORTH_STAIRWAY].setDescription("The stairway extends ahead of you, "
                            + "\ncurving ahead to a doorway to the Attic; you can imagine the grand "
                            + "\nentrance one could make down these stairs like in a movie. "
                            + "\nYou must watch your step as some of the steps are cracked and worn-down. "
                            + "\nPieces of broken steps and marble lay across the steps, but there "
                            + "\nare some shaky rails to assist you in your flight. "
                            + "\nHint: Before entering the Attic and fighting the ghost, keep in mind "
                            + "\nthe following questions: Do you have a way to destroy the ghost? "
                            + "\nCan you blind the ghost? Can you then suck up the ghost? Make sure "
                            + "\nyou have the items to do.");
                        score+=20;
                    }
                    else 
                    {
                        System.out.println("Sorry, there is no piano sheet music in your backpack.");
                    }
                }
            }
            if (phrase.equals("piano with piano sheet music") && currentArea == PIANO_ROOM)
            { 
                if (backpack.indexOf("piano sheet music")>=0)
                {   //remove the item from your backpack
                    backpack.remove(backpack.indexOf("piano sheet music"));
                    world[PIANO_ROOM].setDescription("The grand piano is the centerpiece of the piano room," 
                        + "\nalmost taking up the entire room. A bright, illuminated "
                        + "\nchandelier with diamond-like crystals hovers over"
                        + "\nand illuminates the piano. Empty chairs that "
                        + "\nwould typically be filled with admirers surround the piano, "
                        + "\nand you can imagine the round of applause the piano player would receive. ");
                    System.out.println("You hear the sound of the beautiful piano music ring through the empty mansion. "
                    + "\nSoon, after a brief period of deadly silence, you hear the melody repeat "
                    + "\nback to you, coming from the attic on the north side of the mansion, giving you a hint where "
                    + "\nthe ghost is. ");
                    score+=20;
                }
                else 
                {
                    System.out.println("Sorry, there is no piano sheet music in your backpack.");
                }
            }
            
            else if (currentArea != PIANO_ROOM)
            {
                System.out.println("I don't see anything to play here!");
            }
        }
        
        if (command.equals("give") || command.equals("feed"))
        {
            if (phrase.equals("dog") && currentArea == EAST_STAIRWAY)
            { 
                System.out.println("What do you want to feed the dog with?"); 
                getUserInput();
                if (command.equals("bone"))
                {
                     if (backpack.indexOf("bone")>=0)
                     {   
                         //remove the item from your backpack
                         backpack.remove(backpack.indexOf("bone"));
                         System.out.println("You toss the dog a bone, which it happily feasts on. As it is now distracted, "
                            + "\nyou can proceed north.");
                         world[EAST_STAIRWAY].setDescription("The stairway continues ahead of you. Many of "
                            + "\nthe steps you are walking on are made of cracked pieces "
                            + "\nof marble, and there is a freezing cold metal rail for support. "
                            + "\nStone brick walls enclose you on both sides, and an open "
                            + "\narch to a dangerous, dark room lies to the north of "
                            + "\nthe stairway. You can see part of the patchy floor of the north room.");
                         world[EAST_STAIRWAY].setNorth(BASEMENT);
                         world[BASEMENT].setSouth(EAST_STAIRWAY);
                         score+=15;
                     }
                     else 
                     {
                            System.out.println("Sorry, there is no bone in your backpack.");
                     }
                }
                else
                {
                    System.out.println("You can't feed the dog with a " + command + "!");
                }
            }
            else if (phrase.equals("dog bone") || phrase.equals("bone") || phrase.equals("dog with bone") && currentArea == EAST_STAIRWAY) 
            { 
                // Unlock door
                if (backpack.indexOf("bone")>=0)
                {   //remove the item from your backpack
                    backpack.remove(backpack.indexOf("bone"));
                    System.out.println("You toss the dog a bone, which it happily feasts on. As it is now distracted, "
                        + "\nyou can proceed north.");
                    world[EAST_STAIRWAY].setDescription("The stairway continues ahead of you. Many of "
                        + "\nthe steps you are walking on are made of cracked pieces "
                        + "\nof marble, and there is a freezing cold metal rail for support. "
                        + "\nStone brick walls enclose you on both sides, and an open "
                        + "\narch to a dangerous, dark room lies to the north of "
                        + "\nthe stairway. You can see part of the patchy floor of the north room.");
                    world[EAST_STAIRWAY].setNorth(BASEMENT);
                    world[BASEMENT].setSouth(EAST_STAIRWAY);
                    score+=15;
                }
                else 
                {
                    System.out.println("Sorry, there is no bone in your backpack.");
                }
            }
            else if (currentArea != EAST_STAIRWAY)
            {
                System.out.println("I don't see anything to " + command + " here!");
            } 
            
        }
        
        if (command.equals("put") ||command.equals("wear"))
        {
            if (phrase.equals("night vision goggles") || phrase.equals("on night vision goggles"))
            { 
                if (backpack.indexOf("night vision goggles")>=0)
                {   
                    //remove the item from your backpack
                    wearingNightGoggles=true;
                    backpack.remove(backpack.indexOf(phrase));
                    world[BASEMENT].setDescription("The basement is filled with various knick knacks and "
                        + "\nempty boxes. The room almost feels moist and humid, water "
                        + "\nentrapped within the stone walls and floor. In contrast to "
                        + "\nthe regal feel of the rest of the mansion, the basement "
                        + "\nfeels like a medieval dungeon. However,one object stands "
                        + "\nout in particular, a large glowing vacuum cleaner with almost "
                        + "\nmagnetic properties. ");
                    world[BASEMENT].addItem("vacuum cleaner");
                }
                else 
                {
                    System.out.println("Sorry, there are no night vision goggles in your backpack.");
                }
            }
        }
        
        
        if (command.equals("stun") || command.equals("blind") || command.equals("light"))
        { 
            if (phrase.equals("ghost") && currentArea == ATTIC)
            { 
                System.out.println("What do you want to " + command + " the ghost with?"); 
                getUserInput();
                if (command.equals("strobulb"))
                {
                    // Unlock door
                    if (backpack.indexOf("strobulb")>=0)
                    {   
                        //remove the item from your backpack
                        backpack.remove(backpack.indexOf("strobulb"));
                        System.out.println("A bright light emits from the strobulb, lighting up the room."
                            + "\nYou can now see the ghost who you just stunned.");
                        world[ATTIC].setDescription("To your surprise, the attic is empty and devoid of any "
                            + "\nboxes or items. All that resides in the attic is a "
                            + "\nlayer of dust that coats the wooden floor. The floor is "
                            + "\nfull of perilous holes. Somewhere in the room, "
                            + "\nyou hear a rat squeak and run across.  Most noticeable "
                            + "\nis the freezing temperatures in the room and the "
                            + "\ncold spots. You hear the voice of the ghost taunting "
                            + "\nyou from all directions of the room, and with the "
                            + "\nstrobulb on, you can see the outline of the ghost: "
                            + "\na beautiful woman with a flowing blue gown in the "
                            + "\ncenter of the room. Contrasting with her gown "
                            + "\nis a big red angry scar on her head and her glowing "
                            + "\nangry eyes. She is clearly unhappy but "
                            + "\ndisorientated after being stunned. ");
                            useStrobulb=true;
                            score+=20;
                    }
                    else 
                    {
                        System.out.println("Sorry, there is no strobulb in your backpack.\n");
                    }
                }
            }
            else if (phrase.equals("ghost with strobulb") && currentArea == ATTIC) 
            { 
                if (backpack.indexOf("strobulb")>=0)
                {   
                    //remove the item from your backpack
                    backpack.remove(backpack.indexOf("strobulb"));
                    System.out.println("A bright light emits from the strobulb, lighting up the room."
                        + "\nYou can now see the ghost who you just stunned.");
                    world[ATTIC].setDescription("To your surprise, the attic is empty and devoid of any "
                        + "\nboxes or items. All that resides in the attic is a "
                        + "\nlayer of dust that coats the wooden floor. The floor is "
                        + "\nfull of perilous holes. Somewhere in the room, "
                        + "\nyou hear a rat squeak and run across.  Most noticeable "
                        + "\nis the freezing temperatures in the room and the "
                        + "\ncold spots. You hear the voice of the ghost taunting "
                        + "\nyou from all directions of the room, and with the "
                        + "\nstrobulb on, you can see the outline of the ghost: "
                        + "\na beautiful woman with a flowing blue gown in the "
                        + "\ncenter of the room. Contrasting with her gown "
                        + "\nis a big red angry scar on her head and her glowing "
                        + "\nangry eyes. She is clearly unhappy but "
                        + "\ndisorientated after being stunned. ");
                    useStrobulb=true;
                    score+=20;
                }
                else 
                {
                    System.out.println("Sorry, there is no strobulb in your backpack.\n");
                }
            }
            else if (currentArea != ATTIC)
            {
                System.out.println("I don't see anything to " + command + " here!\n");
            } 
        }
        
        if (command.equals("suck") || command.equals("kill"))
        { 
            if (phrase.equals("ghost") && currentArea == ATTIC)
            { 
                System.out.println("What do you want to " + command + " the ghost with?"); 
                getUserInput();
                if (command.equals("vacuum") && phrase.equals("cleaner") || command.equals("vacuum"))
                {
                    if (backpack.indexOf("vacuum cleaner")>=0)
                    {   
                        //remove the item from your backpack
                        backpack.remove(backpack.indexOf("vacuum cleaner"));
                        System.out.println("You can start to grasp the vacuum cleaner as it emits a strong pull."
                            + "\nThe ghost, clearly resisting, tries to evade the magnetic pull of the vacuum, but fails."
                            + "\nYou see her slowly get sucked up into the cleaner and into the cleaner cannister.");
                        score+=40;
                        useVacuumCleaner=true;
                    }
                    else 
                    {
                        System.out.println("Sorry, there is no vacuum cleaner in your inventory.");
                    }
                }
            }
            else if (phrase.equals("ghost with vacuum cleaner") || phrase.equals("ghost with vacuum") && currentArea == ATTIC) 
            { 
                // Unlock door
                if (backpack.indexOf("vacuum cleaner")>=0)
                {   
                    //remove the item from your backpack
                    backpack.remove(backpack.indexOf("vacuum cleaner"));
                    System.out.println("You can start to grasp the vacuum cleaner as it emits a strong pull."
                        + "\nThe ghost, clearly resisting, tries to evade the magnetic pull of the vacuum, but fails."
                        + "\nYou see her slowly get sucked up into the cleaner and into the cleaner cannister.");
                    score+=40;
                    useVacuumCleaner=true;
                }
                else 
                {
                    System.out.println("Sorry, there is no vacuum cleaner in your inventory.");
                }
            }
            else if (currentArea != ATTIC)
            {
                System.out.println("I don't see anything to " + command + " here!");
            } 
        }
        
        if (currentArea==BASEMENT && wearingNightGoggles==false)
        {
            System.out.println("Too bad that you aren't wearing night vision goggles. "
                + "\nYou should have known that the basement was dangerous and "
                + "\nthat the floor full off holes. Because you "
                + "\ncouldn't see inside the dark room, now you "
                + "\nhave died from falling through the ground. Such a tragic death.");
            gameOver=true;
        }
        
        if (currentArea==ATTIC && useVacuumCleaner==true && useStrobulb==false)
        {
            System.out.println("Wow- you really tried to kill the ghost without "
                + "\nbeing able to see where it is. You should have used the "
                + "\nstrobulb first. Oh well, you're dead now anyways.");
            gameOver=true;
        }

        if (currentArea==ATTIC && backpack.indexOf("vacuum cleaner")<0 || backpack.indexOf("strobulb")<0 && useStrobulb!=true && useVacuumCleaner!=true)
        {
            System.out.println("Woops you wandered into the room where the ghost was hiding. "
                + "\nBut now it's game over since you were unprepared. Did you really "
                + "\nthink you could kill a ghost without any the proper ghost "
                + "\nhunting tools?");
            gameOver=true;
        }
        
        if (currentArea==ATTIC && useVacuumCleaner==true && useStrobulb==true)
        {
            System.out.println("Congratulations. You have completed your task of defeating the ghost Elizabeth. "
                + "\nThrough your journey, you have learned that the relative that enlisted you to "
                + "\nhunt the ghost is the descendant of the Elizabeth's murder: her jealous sister. "
                + "\nDuring Elizabeth's party, she and her sister got into an argument in the library."
                + "\nThe sister, out of anger, grabbed a candlestick from the wall and stabbed and killed Elizabeth."
                + "\nRealizing the severity of her actions, the sister dragged and hid the corpse of her sister "
                + "\nin the attic.");
            System.out.println();
            gameOver=true;
        }
    }

    
    private static void move(String direction)
    {
        if (direction.equals("north") || direction.equals("n"))
        {
            if (world[currentArea].getNorth() == NO_EXIT)
                System.out.println ("There is no exit to the north.");
            else
                currentArea = world[currentArea].getNorth();
        }

        if (direction.equals("south") || direction.equals("s"))
        {
            if (world[currentArea].getSouth() == NO_EXIT)
                System.out.println ("There is no exit to the south.");
            else
                currentArea = world[currentArea].getSouth();
        }

        if (direction.equals("east") || direction.equals("e"))
        {
            if (world[currentArea].getEast() == NO_EXIT)
                System.out.println ("There is no exit to the east.");
            else
                currentArea = world[currentArea].getEast();
        }

        if (direction.equals("west") || direction.equals("w"))
        {
            if (world[currentArea].getWest() == NO_EXIT)
                System.out.println ("There is no exit to the west.");
            else
                currentArea = world[currentArea].getWest();
        }

        if (direction.equals("southeast") || direction.equals("se"))
        {
            if (world[currentArea].getSoutheast() == NO_EXIT)
                System.out.println ("There is no exit to the southeast.");
            else
                currentArea = world[currentArea].getSoutheast();
        }

        if (direction.equals("southwest") || direction.equals("sw"))
        {
            if (world[currentArea].getSouthwest() == NO_EXIT)
                System.out.println ("There is no exit to the southwest.");
            else
                currentArea = world[currentArea].getSouthwest();
        }

        if (direction.equals("northwest") || direction.equals("nw"))
        {
            if (world[currentArea].getNorthwest() == NO_EXIT)
                System.out.println ("There is no exit to the northwest.");
            else
                currentArea = world[currentArea].getNorthwest();
        }

        if (direction.equals("northeast") || direction.equals("ne"))
        {
            if (world[currentArea].getNortheast() == NO_EXIT)
                System.out.println ("There is no exit to the northeast.");
            else
                currentArea = world[currentArea].getNortheast();
        }

        if (direction.equals("up") || direction.equals("u"))
        {
            if (world[currentArea].getUp() == NO_EXIT)
                System.out.println ("There is no exit up.");
            else
                currentArea = world[currentArea].getUp();
        }

        if (direction.equals("down") || direction.equals("d"))
        {
            if (world[currentArea].getDown() == NO_EXIT)
                System.out.println ("There is no exit down.");
            else
                currentArea = world[currentArea].getDown();
        }
    }

    private static void displayFarewellMessageAndScore()
    {
        
        System.out.println();
        System.out.print(" .-') _    ('-. .-.   ('-.           ('-.       .-') _  _ .-') _ \n");  
        System.out.print("(  OO) )  ( OO )  / _(  OO)        _(  OO)     ( OO ) )( (  OO) )  \n");
        System.out.print("/     '._ ,--. ,--.(,------.      (,------.,--./ ,--,'  \\     .'_  \n");
        System.out.print("|'--...__)|  | |  | |  .---'       |  .---'|   \\ |  |\\  ,`'--..._) \n");
        System.out.print("'--.  .--'|   .|  | |  |           |  |    |    \\|  | ) |  |  \\  ' \n");
        System.out.print("   |  |   |       |(|  '--.       (|  '--. |  .     |/  |  |   ' | \n");
        System.out.print("   |  |   |  .-.  | |  .--'        |  .--' |  |\\    |   |  |   / : \n");
        System.out.print("   |  |   |  | |  | |  `---.       |  `---.|  | \\   |   |  '--'  / \n");
        System.out.print("   `--'   `--' `--' `------'       `------'`--'  `--'   `-------'\n");
        System.out.println("\n");
        System.out.println("You scored " + score + " points out of 100.\n");
        System.out.print("This means you have achieved the rank of ");
        if (score==0)
        {
            System.out.print("Ghost Hunted.");
        }
        else if (score<20)
        {
            System.out.print("Afterlife Amateur.");
        }
        else if (score<40)
        {
            System.out.print("Death Dancer.");
        }
        else if (score<60)
        {
            System.out.print("Mediocre Ghost Buster.");
        }
        else if (score<80)
        {
            System.out.print("Strobulb Stunner.");
        }
        else if (score<=99)
        {
            System.out.print("Professional Ghost Hunter.");
        }
        else 
        {
            System.out.print("Ghost Slayer.");
        }
    }
}
 