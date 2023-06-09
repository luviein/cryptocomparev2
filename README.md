# TASK ONE

## MODEL CLASS
1. Set up Model class and input all the relevant properties.
2. Set up SetJSON to set properties from JSON string to Java Objects
3. Set up CreateJSON to send Java Objects into JSON


## SERVICE CLASS
1. Set up getArticles method
    - Create URI Components Builder & include necessary query params

2. Create RestTemplate and ResponseEntity to get json data from API call




# TASK TWO

## CONTROLLER CLASS
1. Create GetMapping (/) to retrieve list of latest articles using Service class
2. Return the latest articles in HTML view

### SAVING ARTICLES 
1. Create PostMapping (/articles) -> Directs to /articles when Save button is pressed
2. Retrieve full list again to compare IDs
3. Compare selected IDS(from requestparam) against all available article IDs
    - If selected IDs dont match with available IDs, it is removed from list



## HTML
1. Display all properties found in Model
2. Add a checkbox to save articles




# TASK THREE

## REPOSITORY CLASS
1. Add method to save list of selected articles by using opsForValue().set()

## SERVICE CLASS
1. Call save method from repository in service class
2. Add the method to controller

## CONFIG CLASS
1. Set up Redis Config & set up relevant application properties



# TASK FOUR

## REST CONTROLLER CLASS
1. Set up GetMapping to output Json format of saved articles
2. If there is error = Http Status 404


## REPOSITORY CLASS
1. Get article by the ID key

## SERVICE CLASS
1. Get article by ID based on method in repo




# MISTAKES MADE
1. Did not add toString in save method for repo which resulted in class cast exception