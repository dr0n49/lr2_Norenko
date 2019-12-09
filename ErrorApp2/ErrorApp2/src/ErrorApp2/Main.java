package ErrorApp2;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static boolean Function(DBHandler dbHandler) throws SQLException
    {
        System.out.println("Список пунктов:");
        System.out.println("0 - Вывести весь список событий.");
        System.out.println("1 - Вывести весь список типов.");
        System.out.println("2 - Вывести весь список событий за определённую дату.");
        System.out.println("3 - Вывести весь список событий с определённым описанием.");
        System.out.println("4 - Добавить событие.");
        System.out.println("5 - Добавить тип.");
        System.out.println("6 - Удалить книгу.");
        System.out.println("7 - Удалить тип.");
        System.out.println("8 - Обновить информацию о событии.");
        System.out.println("9 - Обновить информацию о типе.");
        System.out.println("10 - Завершить работу.");
        Scanner inNumber = new Scanner(System.in);
        System.out.print("Введите номер пункта: ");
        int number = 333;
        number = inNumber.nextInt();
        switch(number) {
            case 0: {
                List<ErrErrors> errErrors = dbHandler.getAllErrors();
                for (ErrErrors errErrors : errErrors) System.out.println(errErrors.toString());
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 1: {
                List<ErrorsType> errorsTypes = dbHandler.getAllErrorsTypes();
                for (ErrorsType errorsType : errorsTypes) System.out.println(errorsType.toString());
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 2: {
                Scanner in = new Scanner(System.in);
                System.out.print("Введите дату: ");
                int dateIn = in.nextInt();
                List<ErrErrors> dateFind = dbHandler.findErrorsByDate(Integer.toString(dateIn));
                for (ErrErrors errErrors : dateFind) System.out.println(errErrors.toString());
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 3: {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите описание: ");
                String descIN = in.nextLine();
                List<ErrErrors> descr = dbHandler.findErrorsByDesc(descIN);
                for (ErrErrors errErrors : descr) System.out.println(errErrors.toString());
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 4: {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите название: ");
                String name = in.nextLine();
                System.out.print("Введите дату: ");
                String date = in.nextLine();
                System.out.print("Введите год описание: ");
                String desc = in.nextLine();
                System.out.print("Введите тип события: ");
                String type = in.nextLine();
                int id = dbHandler.findidbyType(type);
                if (id != 333) {
                    dbHandler.addErrors(new ErrErrors(0, name, date, desc, id));
                    System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                } else
                    System.out.println("\u001B[31m" + "Запрос не отработан (не существует такого типа)" + "\u001B[0m");
                break;
            }
            case 5: {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите тип: ");
                String type = in.nextLine();
                System.out.print("Введите описание типа: ");
                String description = in.nextLine();
                dbHandler.addType(new ErrorsType(0, type, description));
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 6: {
                Scanner in = new Scanner(System.in);
                System.out.print("Введите ID события: ");
                int id = in.nextInt();
                dbHandler.deleteErrors(id);
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 7: {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите тип: ");
                String type = in.nextLine();
                int id = dbHandler.findidbyType(type);
                if (id != 333) {
                    dbHandler.deleteType(id);
                    System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                } else
                    System.out.println("\u001B[31m" + "Запрос не отработан (не существует такого типа)" + "\u001B[0m");
                break;
            }
            case 8: {
                Scanner inInput = new Scanner(System.in);
                System.out.print("Введите ID события: ");
                int idInput = inInput.nextInt();
                Scanner in = new Scanner(System.in, "windows-1251");
                if (!dbHandler.isErrorsEx(idInput)) {
                    System.out.println("\u001B[31m" + "Запрос не отработан (не существует события под таким ID)" + "\u001B[0m");
                    break;
                }
                System.out.print("Введите название события или пропуск (-): ");
                String name = in.nextLine();
                System.out.print("Введите дату или пропуск (-): ");
                String date = in.nextLine();
                System.out.print("Введите описание или пропуск (-): ");
                String desc = in.nextLine();
                System.out.print("Введите тип события или пропуск (-): ");
                String type = in.nextLine();
                int id = dbHandler.findidbyType(type);
                if (id != 333 || type.equals("-")) {
                    dbHandler.updErrors(idInput, name, date, desc, id, type);
                    System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                }
                else System.out.println("\u001B[31m" + "Запрос не отработан (не существует такого типа)" + "\u001B[0m");
                break;
            }
            case 9:
            {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите тип: ");
                String type = in.nextLine();
                int id = dbHandler.findidbyType(type);
                if(id != 333)
                {
                    System.out.print("Введите новое название типа или пропуск (-): ");
                    String typeNew = in.nextLine();
                    System.out.print("Введите описание типа или пропуск (-): ");
                    String description = in.nextLine();
                    dbHandler.updType(id, typeNew, description);
                    System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                }
                else System.out.println("\u001B[31m" + "Запрос не отработан (не существует такого типа)" + "\u001B[0m");
                break;
            }
            case 10: return true;
            default:
            {
                System.out.println("\u001B[31m" + "Пожалуйста выберите пункт..." + "\u001B[0m");
                Function(dbHandler);
            }
        }
        return false;
    }
    static DBHandler dbHandler;
    public static DBHandler GetDBHandler()
    {
        return dbHandler;
    }
    public static void main(String[] args) throws SQLException
    {
        dbHandler = DBHandler.getInstance();
        do
        {
            boolean exit = Function(dbHandler);
            if(exit) break;
        }
        while(true == true);
    }
}
