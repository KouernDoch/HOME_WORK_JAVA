import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.awt.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numberString;
        String[][] busArray;
        int n,totalSheet,unavailable=0,available=0;
        int[] availableByBus,unAvailableByBus;
        int row,tempRow=0,size=3;
        int busId,seatNumber;
        boolean checkAgain=false,checkOption=false,checkMoreBooking=false,again=false,a=false,tablePrinted=false;
        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        // color
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE="\u001B[34m";

        System.out.println("================ Set up Bus ================");
        do{
            System.out.print("-> Enter number of bus: ");
            numberString=scanner.nextLine();
            if (!Pattern.matches("[0-9]+", numberString)) {
                System.out.println(RED+"Allow only number please input again!"+RESET);
                checkAgain = true;
            }else{
                checkAgain = false;
            }
        }while (checkAgain);
        n=Integer.parseInt(numberString);
        do{
            System.out.print("-> Enter number seat of bus: ");
            numberString=scanner.nextLine();
            if (!Pattern.matches("[0-9]+", numberString)) {
                System.out.println(RED+"Allow only number please input again!"+RESET);
                checkAgain = true;
            }else{
                checkAgain = false;
            }
        }while (checkAgain);
       totalSheet=Integer.parseInt(numberString);
       busArray=new String[n][totalSheet];
       availableByBus=new int[n];
       unAvailableByBus=new int[n];
       for(int i=0;i<n;i++){
           for (int j=0;j<totalSheet;j++){
               busArray[i][j]="+";
           }
       }
        do {
            System.out.println("\n=====================================================");
            System.out.println("\t\t\t Bus Management System ");
            System.out.println("=====================================================");
            System.out.println("1, Check Bus");
            System.out.println("2, Booking Bus");
            System.out.println("3, Cancel Booking");
            System.out.println("4, Reset Bus");
            System.out.println("5, Set Row to show record");
            System.out.println("6, Exit");
            System.out.println("------------------------------------------------------------------------");
            do {
                System.out.print("=> Choose Option(1-5): ");
                String option = scanner.nextLine();
                if (!Pattern.matches("[0-9]+", option)) {
                    System.out.println(RED+"Allow only number please input again! "+RESET);
                    checkOption=true;
                }else if (Integer.parseInt(option)>6||Integer.parseInt(option)<=0){
                    System.out.println(RED+"Allow only number 1-6. please input again! "+RESET);
                    checkOption=true;
                } else {
                    switch (option) {
                        case "1":
//                            System.out.println("\n\n*************************************************");
//                            System.out.println("***===      Display All Bus information    ===***");
//                            System.out.println("*************************************************\n");
                            row=size;
                            do {
                               if (row > n) {
                                       int overValuse = row - n;
                                       row = row - overValuse;
                                       tempRow = row - (row % size);
                                }
                               if (row==0) {
                                   row=size;
                                   tempRow=0;
                               }
                               if (n<size){
                                   row=n;
                                   tempRow=0;
                               }
                               if(row==tempRow){
                                   tempRow = row-size;
                               }

                                Table t = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                                t.setColumnWidth(0, 10, 26);
                                t.setColumnWidth(1, 20, 26);
                                t.setColumnWidth(2, 30, 26);
                                t.setColumnWidth(3, 20, 26);
//                                t.setColumnWidth(4, 10, 26);
                                t.addCell("Display All Bus information ", numberStyle, (4));
                                t.addCell(GREEN+" ID"+RESET, numberStyle);
                                t.addCell(GREEN+"Seat"+RESET, numberStyle);
                                t.addCell(GREEN+"Available"+RESET, numberStyle);
                                t.addCell(RED+"Unavailable"+RESET, numberStyle);
//                                t.addCell("STATUS", numberStyle);
//                                System.out.printf(GREEN + "\t%-20s%-20s%-20s%-20s\n\n", "ID", "Seat", "Available", "Unavailable" + RESET);
                                for (int i = tempRow; i < row; i++) {
                                    for (int j = 0; j < totalSheet; j++) {
                                        if (busArray[i][j].equals("+")) {
                                            available = available + 1;
                                        } else if (busArray[i][j].equals("-")) {
                                            unavailable = unavailable + 1;
                                        }
                                    }
                                    availableByBus[i] = available;
                                    unAvailableByBus[i] = unavailable;
                                    available = 0;
                                    unavailable = 0;
                                    t.addCell(String.valueOf((i + 1)), numberStyle);
                                    t.addCell(BLUE + String.valueOf(totalSheet) + RESET, numberStyle);
                                    t.addCell(GREEN + String.valueOf(availableByBus[i]) + RESET, numberStyle);
                                    t.addCell(RED + String.valueOf(unAvailableByBus[i]) + RESET, numberStyle);
//                                    t.addCell(String.valueOf(book[i].getStatus()), numberStyle);
//                                    System.out.printf("\t%-20s%-20s%-20s%-20s\n\n", (i + 1), totalSheet, availableByBus[i], unAvailableByBus[i]);
                                }
                                    System.out.println(t.render());
                                System.out.println(BLUE + "\n1.First\t\t\t2. Next page\t\t\t3,Previous\t\t\t4,Last Page\t\t\t5,See Detail each bus\t\t\t6.Back" + RESET);
                                do {
                                    numberString = scanner.nextLine();
                                    if (!Pattern.matches("[0-9]+", numberString)) {
                                        System.out.println(RED+"Allow only number please input again!"+RESET);
                                        checkAgain = true;
                                    }else if(Integer.parseInt(numberString)<=0||Integer.parseInt(numberString)>6){
                                        System.out.println(RED + "Allow input only number 1-6. Please input again!" + RESET);
                                        checkAgain=true;
                                    }else{
                                        checkAgain=false;
                                    }
                                }while (checkAgain);
                                switch (numberString) {
                                    case "1":
                                            row=size;
                                            tempRow=0;
                                            checkAgain=true;
                                        break;
                                    case "2":
                                        if(n>size) {
                                                row = row + size;
                                                tempRow = row - size;
                                        }else{
                                            row=size;
                                            tempRow=0;
                                        }
                                        checkAgain=true;
                                        break;
                                    case "3":
                                        if(row%size!=0) {
                                            row = row - (row % size);
                                            tempRow=row-size;
                                        } else if (row%size==0) {
                                            row=row-size;
                                            tempRow=row-size;
                                        }
                                        checkAgain=true;
                                        break;
                                    case "4":
                                        if(n%size!=0){
                                            row=n;
                                            tempRow=row-(n%size);
                                        }else{
                                            row=n;
                                            tempRow=row-size;
                                        }
                                        checkAgain=true;
                                        break;
                                    case "5":

                                        do {
                                            System.out.print("Please Input bus's id: ");
                                            numberString = scanner.nextLine();
                                            if (!Pattern.matches("[0-9]+", numberString)) {
                                                System.out.println(RED+"Allow only number please input again!"+RESET);
                                                checkAgain = true;
                                            }else if(Integer.parseInt(numberString)<=0||Integer.parseInt(numberString)>n){
                                                System.out.println(RED + "Allow input only number 1-6. Please input again!" + RESET);
                                                checkAgain=true;
                                            }else{
                                                checkAgain=false;
                                            }
                                        }while (checkAgain);
                                        busId=Integer.parseInt(numberString);

                                        Table t1 = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                                        t1.setColumnWidth(0, 20, 26);
                                        t1.setColumnWidth(1, 20, 26);
                                        t1.setColumnWidth(2, 20, 26);
                                        t1.setColumnWidth(3, 20, 26);
                                        t1.addCell("Display All Seat in Bus "+busId, numberStyle, (4));
                                        for (int i=0;i<busArray[busId-1].length;i++){
                                            if(busArray[busId-1][i].equals("+")) {
                                                t1.addCell(GREEN+"( "+busArray[busId - 1][i]+" ) "+(i + 1)+RESET, numberStyle);
//                                                System.out.printf(GREEN+"\t%-10s ", "(" + busArray[busId - 1][i] + ")" + (i + 1)+RESET);
                                            }else{
                                                t1.addCell(RED+"( "+busArray[busId - 1][i]+" ) "+(i + 1)+RESET, numberStyle);
//                                                System.out.printf(RED+"\t%-10s ", "(" + busArray[busId - 1][i] + ")" + (i + 1)+RESET);
                                            }
//                                            if((i+1)%4 == 0){
//                                                System.out.println("\n");
//                                            }
                                        }
                                        System.out.println(t1.render());
                                        System.out.println("\n\t\t\t\t"+GREEN+"( + ) : Available ( "+availableByBus[busId-1]+" )"+RESET+"\t\t\t\t"+RED+"( - ) : Unavailable ( "+unAvailableByBus[busId-1]+" )"+RESET);
                                        System.out.print("\n=> Please press any key to continue! ");
                                        scanner.nextLine();
                                        checkAgain=false;
                                        break;
                                    case "6":
                                        checkAgain=false;
                                        break;
                                    default:
                                        System.out.println(RED + "Allow input only number 1-6. Please input again!" + RESET);
                                        break;
                                }
                            }while (checkAgain);
                            checkOption=false;
                            break;
                        case "2":
                            do {
                                System.out.print("Please Input bus's id: ");
                                numberString = scanner.nextLine();
                                if (!Pattern.matches("[0-9]+", numberString)) {
                                    System.out.println(RED+"Allow only number please input again!"+RESET);
                                    checkAgain = true;
                                }else if(Integer.parseInt(numberString)<=0||Integer.parseInt(numberString)>n){
                                    System.out.println(RED + "Allow input only number 1-6. Please input again!" + RESET);
                                    checkAgain=true;
                                }else{
                                    checkAgain=false;
                                }
                            }while (checkAgain);

                            busId=Integer.parseInt(numberString);
//                            System.out.println("\n\n*************************************************");
//                            System.out.println("***===      Display All Seat in Bus "+busId+"   ===***");
//                            System.out.println("*************************************************\n");
                            Table t2 = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                            t2.setColumnWidth(0, 20, 26);
                            t2.setColumnWidth(1, 20, 26);
                            t2.setColumnWidth(2, 20, 26);
                            t2.setColumnWidth(3, 20, 26);
                            t2.addCell("Display All Seat in Bus "+busId, numberStyle, (4));
                            for (int i=0;i<busArray[busId-1].length;i++){
                                if(busArray[busId-1][i].equals("+")) {
                                    t2.addCell(GREEN+"( "+busArray[busId - 1][i]+" ) "+(i + 1)+RESET, numberStyle);
//                                    System.out.printf(GREEN+"\t%-10s ", "(" + busArray[busId - 1][i] + ")" + (i + 1)+RESET);
                                    available = available + 1;
                                }else{
                                    t2.addCell(RED+"( "+busArray[busId - 1][i]+" ) "+(i + 1)+RESET, numberStyle);
//                                    System.out.printf(RED+"\t%-10s ", "(" + busArray[busId - 1][i] + ")" + (i + 1)+RESET);
                                    unavailable = unavailable + 1;
                                }
//                                if((i+1)%4 == 0){
//                                    System.out.println("\n");
//                                }
                            }
                            System.out.println(t2.render());
                            System.out.println("\n\t\t\t\t"+GREEN+"( + ) : Available ( "+available+" )"+RESET+"\t\t\t\t"+RED+"( - ) : Unavailable ( "+unavailable+" )"+RESET);
                            unavailable=0;
                            available=0;
                            do {
                                do {
                                    System.out.print("-> Enter seat number to booking: ");
                                    numberString = scanner.nextLine();
                                    if (!Pattern.matches("[0-9]+", numberString)) {
                                        System.out.println(RED + "Allow input only number please input again! " + RESET);
                                        checkAgain = true;
                                    } else if (Integer.parseInt(numberString) > busArray[busId - 1].length || Integer.parseInt(numberString) <= 0) {
                                        System.out.println(RED + "Invalid sheet please input again!" + RESET);
                                        checkAgain = true;
                                    } else if (busArray[busId - 1][Integer.parseInt(numberString)-1].equals("-")) {
                                        System.out.println(RED + "Seat "+Integer.parseInt(numberString)+" is booking already.please input again" + RESET);
                                        checkAgain = true;
                                    } else {
                                        checkAgain = false;
                                    }
                                } while (checkAgain);
                                seatNumber = Integer.parseInt(numberString);
                                do {
                                    System.out.print("=> Do you want to book chair number " + seatNumber + "? (y/n): ");
                                   String checkBooking = scanner.nextLine();
                                    if (checkBooking.equalsIgnoreCase("Y")) {
                                        busArray[busId - 1][seatNumber - 1] = "-";
                                        System.out.println(BLUE + "Sheet number " + seatNumber + " in bus " + busId + " was booked successfully!" + RESET);
                                        checkAgain = false;
                                        a=true;
                                    } else if (checkBooking.equalsIgnoreCase("N")) {
                                        checkAgain=false;
                                        a=false;
                                    } else {
                                        System.out.println(RED+"Wrong please input again!"+RESET);
                                        checkAgain = true;
                                    }
                                } while (checkAgain);
                                do {
                                    if(a) {
                                        System.out.print("=> Do you want to book ticket more? (y/n): ");
                                        String checkbookTicket = scanner.nextLine();
                                        if (checkbookTicket.equalsIgnoreCase("Y")) {
                                            checkMoreBooking = true;
                                            again = false;
                                        } else if (checkbookTicket.equalsIgnoreCase("N")) {
                                            checkMoreBooking = false;
                                            again = false;
                                        } else {
                                            System.out.println(RED + "Wrong please input again!" + RESET);
                                            again = true;
                                        }
                                    }else{
                                        checkMoreBooking = false;
                                        again = false;
                                    }
                                }while (again);
                            }while (checkMoreBooking);
                            checkOption=false;
                            break;
                        case "3":
                            do {
                                System.out.print("Please Input bus's id: ");
                                numberString = scanner.nextLine();
                                if (!Pattern.matches("[0-9]+", numberString)) {
                                    System.out.println(RED+"Allow only number please input again!"+RESET);
                                    checkAgain = true;
                                }else if(Integer.parseInt(numberString)<=0||Integer.parseInt(numberString)>n){
                                    System.out.println(RED + "Allow input only number 1-6. Please input again!" + RESET);
                                    checkAgain=true;
                                }else{
                                    checkAgain=false;
                                }
                            }while (checkAgain);

                            busId=Integer.parseInt(numberString);
//                            System.out.println("\n\n*************************************************");
//                            System.out.println("***===      Display All Seat in Bus "+busId+"   ===***");
//                            System.out.println("*************************************************\n");
                            Table t3 = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                            t3.setColumnWidth(0, 20, 26);
                            t3.setColumnWidth(1, 20, 26);
                            t3.setColumnWidth(2, 20, 26);
                            t3.setColumnWidth(3, 20, 26);
                            t3.addCell("Display All Seat in Bus "+busId, numberStyle, (4));
                            for (int i=0;i<busArray[busId-1].length;i++){
                                if(busArray[busId-1][i].equals("+")) {
//                                    System.out.printf(GREEN+"\t%-10s ", "(" + busArray[busId - 1][i] + ")" + (i + 1)+RESET);
                                    t3.addCell(GREEN+"( "+busArray[busId - 1][i]+" ) "+(i + 1)+RESET, numberStyle);
                                    available = available + 1;
                                }else{
//                                    System.out.printf(RED+"\t%-10s ", "(" + busArray[busId - 1][i] + ")" + (i + 1)+RESET);
                                    t3.addCell(RED+"( "+busArray[busId - 1][i]+" ) "+(i + 1)+RESET, numberStyle);
                                    unavailable = unavailable + 1;
                                }
//                                if((i+1)%4 == 0){
//                                    System.out.println("\n");
//                                }
                            }
                            System.out.println(t3.render());
                            System.out.println("\n\t\t\t\t"+GREEN+"( + ) : Available ( "+available+" )"+RESET+"\t\t\t\t"+RED+"( - ) : Unavailable ( "+unavailable+" )"+RESET);
                            available=0;
                            unavailable=0;
                            do {
                                System.out.print("-> Enter seat number to cancel booking: ");
                                numberString = scanner.nextLine();
                                if (!Pattern.matches("[0-9]+", numberString)) {
                                    System.out.println(RED + "Allow input only number please input again! " + RESET);
                                    checkAgain = true;
                                } else if (Integer.parseInt(numberString) > busArray[busId - 1].length || Integer.parseInt(numberString) <= 0) {
                                    System.out.println(RED + "Invalid sheet please input again!" + RESET);
                                    checkAgain = true;
                                } else if (busArray[busId-1][Integer.parseInt(numberString)-1].equals("+")) {
                                    System.out.println(RED + "Cannot cancel because this seat is not yet booking. please input again!" + RESET);
                                    checkAgain = true;
                                } else {
                                    checkAgain = false;
                                }
                            } while (checkAgain);
                            seatNumber=Integer.parseInt(numberString);
                            do {
                                System.out.print("=> Do you want to book chair number " + seatNumber + "? (y/n): ");
                               String checkBooking = scanner.nextLine();
                                if (checkBooking.equalsIgnoreCase("Y")) {
                                    busArray[busId-1][seatNumber-1]="+";
                                    System.out.println(BLUE + "Seat number " + seatNumber + " in bus " + busId + " was cancel booking successfully!" + RESET);
                                    checkAgain = false;
                                } else if (checkBooking.equalsIgnoreCase("N")) {
                                    checkOption = true;
                                    break;
                                } else {
                                    System.out.println(RED+"Wrong please input again!"+RESET);
                                    checkAgain = true;
                                }
                            } while (checkAgain);

                            checkOption=false;
                            break;
                        case "4":
                            do{
                                System.out.print("-> Enter bus's id to reset:  ");
                                numberString = scanner.nextLine();
                                if (!Pattern.matches("[0-9]+", numberString)) {
                                    System.out.println("Allow only number please input again! ");
                                    checkAgain = true;
                                } else if (Integer.parseInt(numberString) > busArray.length || Integer.parseInt(numberString) <= 0) {
                                    System.out.println(RED+"Invalid bus please input again!"+RESET);
                                    checkAgain = true;
                                } else{
                                    checkAgain=false;
                                }
                            }while (checkAgain);
                            busId=Integer.parseInt(numberString);
                            do {
                                System.out.print("=> Do you want to reset all sheet in bus "+busId+" ? (y/n): ");
                                String checkResetBus = scanner.nextLine();
                                if (checkResetBus.equalsIgnoreCase("Y")) {
                                    for (int s = 0; s < busArray[busId-1].length; s++) {
                                        busArray[busId-1][s]="+";
                                    }
                                    System.out.println(BLUE+"\nBus id "+busId+" was reset successfully!"+RESET);
                                    checkAgain=false;
                                } else if (checkResetBus.equalsIgnoreCase("N")) {
                                    checkOption = true;
                                    break;
                                } else {
                                    System.out.println(RED+"Wrong please input again!"+RESET);
                                    checkAgain=true;
                                }
                            }while (checkAgain);
                            checkOption=false;
                            break;
                        case "5":
                            do {
                                System.out.print("=> Set row to show: ");
                                numberString = scanner.nextLine();
                                if (!Pattern.matches("[0-9]+", option)) {
                                    System.out.println(RED + "Allow only number please input again! " + RESET);
                                    checkAgain = true;
                                } else if (Integer.parseInt(numberString)>n) {
                                    System.out.println(RED + "Error Size of bus is less then size row to show. please input again! " + RESET);
                                    checkAgain = true;
                                }
                            }while (checkAgain);
                            System.out.println(BLUE+"Record will show with "+numberString+" row."+RESET);
                            size=Integer.parseInt(numberString);
                            break;
                        case "6":
                            System.out.println("Good bye :)");
                            System.exit(0);
                            break;
                        default:
                            System.out.println(RED+"Invalid number please input again! "+RESET);
                            checkOption = true;
                            break;
                    }
                }
            }while (checkOption);
        }while (true);
    }
}