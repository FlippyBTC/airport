package com.journaldev.parser.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Airport {
	private static String fileName = "C:\\air\\airports.csv";


	public static void main(String[] args) throws FileNotFoundException {

		int columnNumber = Integer.parseInt(args[0]);

		Scanner scanner = new Scanner(System.in);
		String findText = scanner.nextLine();
		try {
			List<String> lines = Files.readAllLines(Paths.get(fileName));

			long timeStart = System.currentTimeMillis();
			List list = lines.parallelStream()
					.filter(n -> n.split(",")[columnNumber].contains(findText))
					.sorted((o1, o2) -> o1.split(",")[columnNumber].compareTo(o2.split(",")[columnNumber]))
					.collect(Collectors.toList());
			int count = list.size();
			long timeEnd = System.currentTimeMillis();


			System.out.println(count);
			System.out.println(String.join("\n", list));
			System.out.println(timeEnd - timeStart + "мс");

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
