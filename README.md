# About the project

Tennis Score Board is a small client-server application with a web interface that implements a table for calculating points between players during a tennis game, as well as viewing already completed games between players.

## Built with

* Java 21
* Maven
* Jakarta
* Hibernate
* H2
* JUnit
* Docker

## Requests

### Main page

* Contains links to the new match and completed match list pages.

### New match page

The address is `/new-match`.

#### Interface:

* HTML form with fields **Player name 1**, **Player name 2** and button **start**. For simplicity, let's assume that player names are unique. A player cannot play with himself.
  
* Clicking the **start** button results in a POST request to `/new-match`.
  
#### POST request handler:

* Checks for the existence of players in the `Players` table. If a player with this name does not exist, it create one.
  
* Create an instance of the `Match` class (containing players' IDs and current score) and put it into the collection of current matches. The key of the collection is the UUID, the value is the `Match` class instance.
  
* Redirect to the page `/match-score?uuid=$match_id`.
  
### Match score page

The address is `/match-score?uuid=$match_id`.

GET parameter `uuid` contains the UUID of the match.

#### Interface:

* Table with **player names**, **current score**.

* Forms and buttons for actions - **player 1 won the current point**, **player 2 won the current point**.

* Clicking the buttons leads to a POST request to `/match-score?uuid=$match_id`, the fields of the submitted form contain the id of the player who won the point.

#### POST request handler:

* Retrieves an instance of the Match class from the collection

* According to which player won the point, updates the match score
  
* If the match is not over - renders the match score table with the buttons described above
  
* If the match is over:
  
  * Delete the match from the collection of current matches
  * Write the finished match to SQL database
  * Rendering the final score
  

### Played matches page

The address is `/matches?page=$page_number&filter_by_player_name=$player_name`. 

GET parameters:

* `page` - page number. If the parameter is not specified, the first page is assumed to be the first page

* `filter_by_player_name` - name of the player whose matches we are looking for. If the parameter is not set, all matches are displayed

Page-by-page displays the list of matches played. Allows you to search for matches of a player by his name. Pagination implementation will be required for paginated display.

#### Interface:

* Form with filter by player name. An input field for the name and a **search**  button. A GET request of the form `/matches?filter_by_player_name=${NAME}` is generated when clicked.
  
* Table of matches found.
  
* Page switch, if more matches are found than can fit on one page.

## Installation

The application is launched using Docker. Before using further commands, make sure you have it and have it updated.

- To get started, you need to copy this repository:

```bash
git clone https://github.com/Faust32/tennis-scoreboard.git
```

- After that, go to the directory with the repository and run the command:

```bash
docker build -t tennis-score-board .
```

- All that's left is to start the container:

```bash
docker run -d -p 8080:8080 tennis-score-board .
```

- The application will then be available at `http://localhost:8080/`.
