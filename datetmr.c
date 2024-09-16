#include <stdio.h>
#include <stdlib.h>

//define months
#define JAN 1
#define FEB 2
#define MAR 3
#define APR 4
#define MAY 5
#define JUN 6
#define JUL 7
#define AUG 8
#define SEP 9
#define OCT 10
#define NOV 11
#define DEC 12

//define month lengths
#define FEB_LENGTH 28
#define LENGTH_30 30
#define LENGTH_31 31


int main(int argc, char** argv) {

    int day;
    int month;
    int year;
    int days_in_month;

    printf("Enter today's date (dd/mm/yyyy): ");
    scanf("%d/%d/%d", &day, &month, &year);

    

//check validity
    // years
    if (year < 1753) {
        printf("The year %d is invalid. Please try again.\n", year);
        exit(EXIT_FAILURE);
    }
    
    // months
    if (month > 12) {
        printf("The month %d is invalid. Please try again.\n", month);
        exit(EXIT_FAILURE);
    }
    // check month length
    if (month == FEB) {
        days_in_month = FEB_LENGTH;
    } else if (month == APR || month == JUN || month == SEP || month == NOV) {
        days_in_month = LENGTH_30;
    } else {
        days_in_month = LENGTH_31;
    }

    // check leap year
    if (month == FEB && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
        days_in_month = FEB_LENGTH + 1; 
        printf("Days in month: %d\n", days_in_month);
    }
    
    if (day < 1 || day > days_in_month) {
        printf("Invalid day %d. Try again.\n", day);
        exit(EXIT_FAILURE);
    }



// get tomorrow's day
    day += 1;

// check rollovers

// check year rollover
    if (month == 12 && day >= days_in_month) {
        year += 1;
        month = 1;
        day = 1;
    }

// check month rollover
if (day > days_in_month) {
        month += 1;
        day = 1;
    }

//print tomorrow's date
printf("Tomorrow's date is: %d/%d/%4d\n", day, month, year);
    
return 0;   
}
