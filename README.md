# Transporter
Team6 Project Authors:Jay Flowers, Kyle Ruane, Sam Maskey

Project Concept:  Text based resource gathering map traversing adventure game.

## Story: 
You are a truck driver tasked with picking up and delivering State to State payloads. Before you can get on the road you must collect the necessary items from your home office.   (With phase II & III incorporated the driver will encounter roadblocks along the way and have to navigate through a small-scale map to collect items/services to get back on the road)
	
## General Flow:  
1.	The program will select from a set of scenarios indicating home office, pickup and delivery locations
2.	The player will move from room to room in the home office checking off items/resources in order to start driving
3.	The player will navigate from the home office state to the pickup state
4.	The player will navigate from the pickup state to the delivery state
5.	The player will navigate from the delivery state back to the home office state

##### With phase II incorporated:
6.	When the player navigates into a state that has an issue/roadblock the player will navigate through a sub-map collecting items/services needed to get back on the road

##### With phase III incorporated:
7.	There will be a time/movement-based system in place to track how timely the delivery was and/or will show if the delivery was successful based on the time/movement count
8.	There will be immediate game ending scenarios allowed (e.g., you bypassed a warning not to drive through a snow storm without getting snow chains)


## Features:
* Phase I:
    * Home Office (gather items to get on the road) (**MVP**)
    *	Point A to Point B w/out issues to Pick up point
    *	Point A to Point B w/out issues to Delivery point
    *	Point A to Point B w/out issues to back to the office 
    *	Map/Help Menu
*	Phase II:
    *	Add issues/roadblocks
    *	Create maps at issues site to gather needed items to get back on the road 
    *	TBD in sprint planning
*	Phase III:
    *	Scoring/timing system
    *	Add immediate game enders 
    *	Build out more issues/roadblock maps
    *	TBD in sprint planning


## MVP Prototype:

##### At the office:
1. start new game (New player is generated with a random scenario containing (home office state, pickup state, and delivery state) player starts at their truck) Player must collect (x, y, z) items before proceeding to the truck to get on the road
2. move(right) -> moves to Warehouse
3. explore() -> text tells what is in the room 
4. explore(item) -> explains about the item and/or says “you found x”
5. get(x) -> adds x to backback (continue moving, exploring, getting until all items are in backpack and moves back to truck)
6. get on the road

Players can type help to show a map of the area, items in backpack, and items needed to get on the road

##### On the road:
(game changes from home office map based to state to state based)
1. drive(north) -> drives from NM to CO
(continue driving until they reach pickup location)
2. pickupLoad() -> sets hasLoad to true
3. drive(north,south,east,west) 
(continue driving until they reach delivery location)
4. dropOffLoad() -> check if hasLoad is set to true sets hasLoad to false or display message like “you don’t have anything to drop off” set Boolean dropOffComplete to true
5. drive(north,south,east,west) 
(continue driving until they reach home office location) check dropOffComplete is true, if true display End of Game message if false display “go back and make your delivery”

Player can type help at any time to see available directions, and key locations, display Boolean associated values 


