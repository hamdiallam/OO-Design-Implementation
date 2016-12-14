Hamdi Allam
CIS-28
Library System!

PROTOTYPE OF THE LIBRARY SYSTEM:
    1.)All data is loaded from the txt file
        -The program does not save new data
        -Changes must be written in the txt file manually
        -The first line in both txt files indicates Format
    2.)All input are INTEGERS
        -For simplicity, everything is an int
        -Inputting anything else will BREAK the program
    3.)Functions that work!
        -A user can checkout a book
           -Prerequisites
                1.)User must have no fines
                2.)Only teachers can check out references
                3.)No checked out copy can be overdue
                4.)The copy must Exist
           -An exception is thrown if any requirement fails
           -Upon success, the duration of the checkout period is shown
           based on the maxDays for the specific book

        -NEWDAY
            -The user inputs the length of a day in minutes
            and every day, the NEWDAY event in invoked. The newDay()
            function in every copy of the library system will be
            invoked.
        -Return a copy
        -Detailed report on a card OR Book

    4.)Functions that are not implemented
        -Reservation
            - I did not have enough time to implement this
            feature.

EXAMPLE TEST: (Checkout Exceptions are shown/reports/changes)

CIS-28 Library System Prototype!
	-All changes are not persistent and will be erased on exit.
	-Create new records in the text files to add permanent changes.
Enter length of 1 day in minutes: 1
Library Inventory:

All the borrowers in the system:
	ID: 1, Name: Newton, currentFines: 0, numBooks: 0
	ID: 0, Name: Hamdi, currentFines: 0, numBooks: 0
All the books in the system:
	ISBN: 2345, Title: Calculus, Publisher: stewart, numAvailableCopies: 2, isReference: true
	ISBN: 1234, Title: 10 ways to win, Publisher: bob joe, numAvailableCopies: 1, isReference: false

Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 2
Enter cardID: 0
Enter book ISBN: 2345
Cannot checkout: Insufficient Permissions
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 2
Enter cardID: 1
Enter book ISBN: 2345
Checkout successful!
Checkout duration: 1 min
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 4
Enter cardID: 1
ID: 1, Name: Newton, currentFines: 0, numBooks: 1
Checked out Copies:
	Barcode: 1, STATUS: CheckedOut, Days-CheckedOut: 1, isReference: true

Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 5
Enter book ISBN: 2345
ISBN: 2345, Title: Calculus, Publisher: stewart, numAvailableCopies: 1, isReference: true, MaxDays: 1
Copies:
	Barcode: 1, STATUS: CheckedOut, currOwnerId: 1, Days-CheckedOut: 1, isReference: true
	Barcode: 2, STATUS: Available, currOwnerId: Available, Days-CheckedOut: 0, isReference: true
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 5
Enter book ISBN: 2345
ISBN: 2345, Title: Calculus, Publisher: stewart, numAvailableCopies: 1, isReference: true, MaxDays: 1
Copies:
	Barcode: 1, STATUS: Overdue, currOwnerId: 1, Days-CheckedOut: 2, isReference: true
	Barcode: 2, STATUS: Available, currOwnerId: Available, Days-CheckedOut: 0, isReference: true
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 2
Enter cardID: 1
Enter book ISBN: 1234
Cannot checkout: Card Has Existing Overdue Copies
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 3
Enter card id: 1
Copies:
	Barcode: 1, STATUS: Overdue, Days-CheckedOut: 3, isReference: true

Enter copy Barcode: 1
Return successful
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 4
Enter cardID: 1
ID: 1, Name: Newton, currentFines: 20, numBooks: 0
Checked out Copies:

Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 2
Enter cardID: 1
Enter book ISBN: 1234
Cannot checkout: Must Pay fines
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 6
Enter cardID: 1
Enter amount: 20
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 5
Enter book ISBN: 1234
ISBN: 1234, Title: 10 ways to win, Publisher: bob joe, numAvailableCopies: 1, isReference: false, MaxDays: 1
Copies:
	Barcode: 0, STATUS: Available, currOwnerId: Available, Days-CheckedOut: 0, isReference: false
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 1
Library Inventory:

All the borrowers in the system:
	ID: 1, Name: Newton, currentFines: 0, numBooks: 0
	ID: 0, Name: Hamdi, currentFines: 0, numBooks: 0
All the books in the system:
	ISBN: 2345, Title: Calculus, Publisher: stewart, numAvailableCopies: 2, isReference: true
	ISBN: 1234, Title: 10 ways to win, Publisher: bob joe, numAvailableCopies: 1, isReference: false

Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice: 2
Enter cardID: 0
Enter book ISBN: 2345
Cannot checkout: Insufficient Permissions
Menu:
	1.)Print Inventory
	2.)Checkout a book
	3.)Return a book
	4.)Detailed Card report
	5.)Detailed Book report
	6.)Pay Fines
	0.)Exit the Program
Select a choice:


