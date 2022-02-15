package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.Employee;
import entities.HourContract;
import entities.PartTimeEmployee;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Main {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();

		System.out.print("Name: ");
		String workerName = sc.nextLine();

		System.out.print("Level (JUNIOR/MID_LEVEL/SENIOR): ");
		String workerLevel = sc.nextLine();

		System.out.print("Base salary: ");
		double baseSalary = sc.nextDouble();

		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary,
				new Department(departmentName));

		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();

		for (int i = 1; i <= n; i++) {
			System.out.println("Enter contract #" + i + " data:");

			System.out.print("Date (DD/MM/YYYY): ");

			Date contractDate = sdf.parse(sc.next());

			System.out.print("Value per hour: ");

			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();

			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(contract);
		}

		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String monthAndYear = sc.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

		List<Employee> list = new ArrayList<>();

		System.out.print("Enter the number of employees: ");
		int j = sc.nextInt();

		for (int i = 1; i <= j; i++) {
			System.out.println("Employee #" + i + " data:");
			System.out.print("Outsourced (y/n)? ");
			char ch = sc.next().charAt(0);
			System.out.print("Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.print("Hours: ");
			int hours = sc.nextInt();
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			if (ch == 'y') {
				System.out.print("Additional charge: ");
				double additionalCharge = sc.nextDouble();
				list.add(new PartTimeEmployee(name, hours, valuePerHour, additionalCharge));
			} else {
				list.add(new Employee(name, hours, valuePerHour));
			}
		}

		System.out.println();
		System.out.println("PAYMENTS:");
		for (Employee emp : list) {
			System.out.println(emp.getName() + " - $ " + String.format("%.2f", emp.payment()));
		}

		sc.close();

	}

}
