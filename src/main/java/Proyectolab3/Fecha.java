
package Proyectolab3;

public class Fecha {
    int day;
    int month;
    int year;

    // Crear una fecha
    public void Crear_fecha(int day, int month, int year){
        if (year > 0 && month > 0 && month < 13 && day > 0 && day < 32){
            if (month == 4 || month == 6 || month == 9 || month == 11){
                if (day < 31){
                    this.day = day;
                    this.month = month;
                    this.year = year;
                }
            }
            else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                this.day = day;
                this.month = month;
                this.year = year;
            }
            else {
                if (day < 29){
                    this.day = day;
                    this.month = month;
                    this.year = year;
                }
            }

        }
    }
}
