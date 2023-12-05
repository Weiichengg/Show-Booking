# Show-Booking

This is a simple Java application to simulate booking of shows via inputs from the command line.
The testing are done via Maven.

# Maven commands

`mvn install`: Compiles, tests, and installs the package into your local Maven repository.
`mvn test`: Compiles and runs the tests without installing the package. This is typically used for running your unit tests to ensure that your code changes are correct.

# Assumptions

1. We provide the users with 2 sets of interface - `Admin` and `Buyer`. Each Interface has their own `Commands` and the user can toggle between the 2 via `switch` command.
2. `Book`, the seats are not case - sensitive which means both A1 and a1 are considered the same. If any of the seats are invalid (does not exist or is currently booked), this operation will fail.
3. `Setup` Show number is a strictly postive value (> 0)
4. `Cancel` if the phone number does not match the record used to purchase the tickets, this operation will fail.
