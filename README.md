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
