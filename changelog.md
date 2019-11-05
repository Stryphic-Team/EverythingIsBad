### 0.0.1-pre-alpha `9/11/2019`
  * First release (doesn't work)
  
### 0.0.2-pre-alpha `9/12/2019`
  * Fixed crash from CYAN mapColor

### 0.0.21-pre-alpha `9/12/2019`
  * Fixed stack over flow error from mobSpawn event
  * Fixed crash on first join
  
### 0.0.23-pre-alpha `9/16/2019`
  * Fixes
    * Fixed crashes when the config is updated
    * Fixed machines not storing progress
    * Jesus now spawns less profusely in the heaven dimension
    * Re-fixed potion side-effect re-add on rejoin glitch
    * Blizzes don't spawn in heaven now
    * Machines now make sounds when you fill them
    * Question mark blocks now only spawn in the overworld
    * Question mark blocks are less common now, and have better loot
    * Random cubes of wool are less common now
    * Blood lakes don't spawn in the bedrock ceiling of the nether
  * Additions
    * Jesus now drops blood on death
    * You now teleport to the overworld when you 
    fall into the void while in heaven
    * You can now catch the common cold if you stand in the cold 
    biome for too long, if you don't have the immunity
    * Improved question mark block texture
    * You now get high when you jump into God's Pee lakes(the green puddles in the world)
    * You have immunity to common cold if you make your bed in a cold biome
    * Jesus now drops jesus meat and has a 1/10 chance to drop healing potions
    * Added question mark block structures
    * You can craft diaria from water and poop
    * Improved Stupid™ TNT recipe
    * Added urine crystals, urine bricks, urine brick block
    * Mobs now poop
    * Added Laxative item which makes you poop; can be crafted from Medicine Bottle item
    
### 0.1.0-alpha `9/30/2019`
    
  * Additions
    * Machines:
      * Machine GUIs are now modular and received a much needed face lift
      * Machine recipes have been drastically changed, utilizing the new Solenoid and Iron Rod items
      * Liquifier Machine added
      * Machines now have an active texture, and some machines have been fitted with an animation
      * Added Stupid™ Core Reactor
      * Added Ultra Stupid™ Core
      * Added temperature to machines, and a statistics gui
      * Added Coal Generator
    * Generation:
      * Copper Ore with config setting and ore dict compatibility
      * Heaven now has proper terrain generation
      * Added landmines that spawn randomly in the world
    * Mobs:
      * Added Police Officer mob; spawns when you cook a baby in a furnace; he breaks down doors and yells a lot!
      * Police Officer uses a Police Gun item, which shoots Bullet entities
      * Added Bullet entity that can shatter glass and does lots of damage
      * Added Baby item which is dropped rarely and periodically from villagers. Can also be named in an anvil
      * Baby ages up into a child villager after (by default) 1200 seconds.
      * Stupid Skeletons now fire low powered Stupid™ TNT
      * Jesus will now only attack non-Christians
      * Jesus heals you if you have the Blindness effect
      * Added angels to the Heaven dimension
      * Added Angel Dust which drops from Angels, gives Adrenaline effect when eaten
    * Drugs/Potion Effects:
      * Common Cold gives you slowness and weakness
      * Added Adrenaline effect: gives speed and haste
      * You get Levitation effect and then go to Heaven after eating Cooked Body of Christ
      * You can now eat Devil's Cabbage and Body of Christ without being hungry
      * Animals can get high from God's Pee
    * Miscellaneous:
      * COFH Core is now a dependency 
      * All config settings can be adjusted with the Forge config manager
      * Added a system of currency (WIP)
      * Added a stats command (/everythingbad:stats) to get your Everything is Bad statistics
      * Added religion: players will now spawn with a tag for the religion that they are assigned to
      * Souls now have infinite soul bound
      * Cloud blocks are now transparent
      * Added partial Jowali language support, Jowali font
      
  * Fixes
    * Twin towers now actually spawn
    * Poop Brick Fence has the same material as Nether Brick Fence
    * Heaven is no longer laggy
    * Fixed block inheritance so block properties are more consistent in later releases 
    * Fixed issue with souls being giving new souls to players when they die and when the server restarts
    
### 0.1.1-alpha `10/8/19`
 
  * Fixes
    * Blood spawning on death is now false by default
    * Ghost blood fluid when client config and server config aren't synced is fixed
    * Happy leaves actually drop devils cabbage instead of always dropping saplings
    * Added poop to the ore dictionary for dyeBrown and fertilizer
    * Machines speed multiplier is now working as it should
    * Jowali religion can now be obtained
    * Diaric Generators don't accept any forge fluid with a bucket now
    * Babies don't age in doubles 
    * Commands now work without being opped
    * Machines don't reset their progress if they don't have enough energy
    * Happy trees don't spawn on super flat anymore
    * Fixed the stupid core not dropping after highness effect ends
  * Added
    * Added Adrenaline potion effect
    * Added adrenaline potion
    * Machine guis now have actual tooltips
    
### 0.2.0-alpha `11/1/19`

  * Added
    * Machines:
      * Added ATM Machine
      * Added Slot Machine
      * Added Quarry
      * Added incubator
    * Generation:
      * Added Stupid™ Facility
      * Added Casinos
      * Urine Crystals now spawn the nether similar to glowstone
    * Drugs/Potion Effects:
      * Added withdrawal symptoms(For tobacco, angel dust)
      * Added Stimulation potion effect
    * Economy:
      * Money is now a floating point(so you can have cents now)
      * You now have a chance of being a student(6 percent chance) when you first join and start with debt that you have to pay off($1000 to $20,000).
      * There are now tasks that you can do to obtain money(Fishing, Mob Hunting, and Mining)
    * Miscellaneous:
      * You now get xp for raising a baby
      * Added a sub command system for the everythingisbad command
      * Added Random Teleport Command(RTP) for ops only
      * Added Help command
      * Added Heart rate monitor
      * Custom records are now obtainable
      * Added cigarettes
      * Added cigars
      * There is now a 2% chance that a ultra Stupid™ Core will drop when you or a mob goes through the highness effect
      * Added liquifier recipe :-( 
      * Added flashy block which spawns in the stupid™ facility reactor room and the casino
      * Added Heart attack when your heart rate gets to high
      * Angel dust is now less potent
      
    * Visual Enhancements
      * Added a new look for the statistics page
      * Added new ultra stupid core texture
      * Player now has a heart rate which can be viewed with a heart rate monitor
  * Fixes
    * Adrenaline is now only 3 minutes long by default
    * Babies aged up can now have all villager professions
    * Fixed ore dictionary for happy tree saplings
    * Fixed ghost stupid cores when mobs finish the highness effect
    * Fixed the immediate destruction when you are on a server of your player file when you use angel dust
    * Removed Blood spawning on death
### 0.2.1-alpha `11/4/19`
   * Fixed triple interest rate return
   * Fixed client crash when withdrawing fractional amounts from the ATM
   * Giving money to a another player now notifies the player that they recieved the mone

