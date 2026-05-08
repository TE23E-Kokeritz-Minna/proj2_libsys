### **ID related problems**
- [x] Need unique id for every category 
- [x] In constructor genereate new ID 
    - [x] **method**
        - For loop until the list nolonger includes the id, starting at 1
    - [x] get method for the registers 

### **Basic LibrarySystem updates**
- [X] Get method for Registers 
- [ ] Static block getAll data of every type

    - #### **Menu**
        - [ ] Figure out methods for add, remove and so forth
        - [ ] Move under Menu into seperate methods (cleans up)
        - [ ] TODO Search 
            - Both item and User
            - ask for title
            - ask for name
            - look through list, see if contians. 
        - [ ] TODO write out sorted
            - Item, title alphabetic 
            - User, name alphabetic
        - [ ] TODO loan capabilities 
            - ask for ID or name 
            - see if SuspendedRegister contains that userID

### Generall checks 
- [ ] is Client Error codes correctly structured
- [X] why have a literature list and not Book and Magazine 
    - removed literature 
- [ ] duplicate ids in Literature (different Types), problem? mayhaps
    - will still be problem for Loan, but not in Register