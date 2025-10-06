package backend.jam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.*;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class LearnJava {

	@Test
	@DisplayName("Фильтрация имен по первой букве с преобразованием в верхний регистр")
	public void testFilterNamesByFirstLetterAndUppercase() {
		// Тест демонстрирует фильтрацию имен, начинающихся с определенной буквы, и преобразование в верхний регистр
		// Результат: List<String> с именами, начинающимися на "А", преобразованными в верхний регистр

		List<String> names = List.of("Аня", "Борис", "Вика", "Андрей");
		List<String> filtered =
				names.stream().filter(name -> name.startsWith("А")).map(String::toUpperCase).toList();

		System.out.println(filtered);
		// Вывод: [АНЯ, АНДРЕЙ]
	}

	@Test
	@DisplayName("Фильтрация по длине, сортировка и преобразование в верхний регистр")
	public void testFilterSortAndUppercase() {
		// Тест демонстрирует комплексную обработку: фильтрация по длине > 4, сортировка, преобразование в верхний регистр
		// Результат: List<String> с отфильтрованными, отсортированными и преобразованными именами

		List<String> names = List.of("Armen", "Nina", "Marsel");
		List<String> filtered = names.stream().filter(name -> name.length() > 4)
				//.sorted()
				.map(String::toUpperCase).toList();

		System.out.println(filtered);
		// Вывод: [ARMEN, MARSEL]
	}

	@Test
	@DisplayName("Фильтрация четных чисел")
	public void testFilterEvenNumbers() {
		// Тест демонстрирует фильтрацию четных чисел из списка
		// Результат: List<Integer> только с четными числами

		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
		List<Integer> result = numbers.stream()
				.filter(number -> number % 2 == 0 && number > 2)  // boolean && boolean
				.collect(Collectors.toList());

		System.out.println(result);
		// Вывод: [2, 4, 6]
	}

	@Test
	@DisplayName("Поиск индекса максимального элемента в списке")
	public void testFindMaxElementIndex() {
		// Тест демонстрирует поиск индекса максимального элемента в списке чисел
		// Результат: индекс (int) позиции максимального элемента в списке

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		int max = Collections.max(numbers);
		int index = numbers.indexOf(max);

		System.out.println(index);
		// Вывод: 6
	}

	@Test
	public void tableUmnoj() {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				System.out.print(i * j + "\t");
			}
			System.out.println();
		}
	}

	@Test
	public void tableUmnojWhile() {
		int i = 1;
		while (i < 10) {
			int j = 1;
			while (j < 10) {
				System.out.print(i * j + "\t");
				j++;
			}
			System.out.println();
			i++;
		}
	}

	@Test
	public boolean isPalindrome(String word) {
		int left = 0;
		int right = word.length() - 1;
		while (left < right) {
			if (word.charAt(left) != word.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	@Test
	public void chch() {
		isPalindrome("шалаш");
	}

	@Test
	public void skksk(String word) {
		//String reversed = new StringBuilder(word).reverse().toString();
		//return word.equals(reversed);
	}

	@Test
	@DisplayName("Фильтрация строк после замены символов")
	public void testStringReplacementAndFiltering() {
		// Тест демонстрирует замену символов в строках с последующей фильтрацией по точному совпадению
		// Результат: List<String> только с теми строками, которые после замены соответствуют заданному условию

		List<String> nums = Arrays.asList("num-1", "num-2", "num-3");
		List<String> result =
				nums.stream().map(n -> n.replaceAll("-", " ")).filter(n -> n.equals("num 2")).collect(Collectors.toList());

		//result.forEach(System.out::println);
		System.out.println(result);
		// Вывод: [num 2]
	}

	@Test
	@DisplayName("Создание строки из повторяющихся элементов с разделителем")
	public String testStringRepeatingWithSeparator(String word, String separator, int count) {
		// Метод демонстрирует создание строки из заданного количества повторений слова, соединенных разделителем
		// Результат: строка с повторяющимися элементами, разделенными указанным символом/строкой

		return Collections.nCopies(count, word).stream().collect(Collectors.joining(separator));
	}

	@Test
	@DisplayName("Демонстрация создания повторяющейся строки")
	public void testRepeatingStringDemo() {
		// Тест демонстрирует использование метода для создания строки из повторяющихся элементов
		// Результат: строка "test" повторенная 10 раз через разделитель "O"

		System.out.println(testStringRepeatingWithSeparator("test", "O", 10));
		// Вывод: testOtestOtestOtestOtestOtestOtestOtestOtestOtest
	}

	@Test
	public void jjskkk() {
		int a = 10;
		int b = 12;
		System.out.println(multiply(3));
		System.out.println(multiply(3, 4));
	}

	public int multiply(int number) {
		return number * 2;
	}

	public int multiply(int number, int multiply) {
		return number * multiply;
	}

	@Test
	public void dsdssdsd(String text, int width, int height) {
		if (width < 3) {
			System.out.println("Ширина не может быть менее 3-х");
			return;
		}
		if (height < 3) {
			System.out.println("Высота не может быть менее 3-х");
			return;
		}
		if (text.length() > width) {
			System.out.println("Текст превышает ширину");
			return;
		}
		String top = "#".repeat(width);
		String empty = "#" + " ".repeat(width - 2) + "#";
		String middle = "# " + text + " ".repeat(width - text.length() - 3) + "#";
		System.out.println(top);
		for (int i = 0; i < height - 2; i++) {
			System.out.println(i == (height - 2) / 2 ? middle : empty);
		}
		System.out.println(top);
	}

	@Test
	public void pdpdp() {
		dsdssdsd("Hellto world", 17, 6);
	}

	@Test
	public void assertFail() {
		int expected = 20;
		int actual = 5 * 5;
		Assertions.assertTrue(actual == expected, "5 * 5 != 25");
	}

	@Test
	public void ddsdssw() {
		int[] a = new int[] {2, 3, 5};
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; ++i) {
			b[i] = a[i] * 2;
		}
		System.out.println(Arrays.toString(b));
	}

	@Test
	@DisplayName("Замена символов в строках списка")
	public void testStringCharacterReplacement() {
		// Тест демонстрирует замену определенных символов в каждой строке списка
		// Результат: List<String> с измененными строками (дефисы заменены на пробелы)

		List<String> names = Arrays.asList("Armen-1", "Armen-1", "Nina-1", "Marsel-1");
		names = names.stream().map(x -> x.replaceAll("-", " "))
				//.distinct()
				.collect(Collectors.toList());

		System.out.println(names);
		// Вывод: [Armen 1, Armen 1, Nina 1, Marsel 1]
	}

	@Test
	@DisplayName("Демонстрация порядка выполнения map и filter с логированием")
	public void testStreamOperationsOrder() {
		// Тест демонстрирует порядок выполнения операций map и filter в Stream
		// Показывает, как каждый элемент проходит через всю цепочку операций
		// Результат: List<String> с отфильтрованными и преобразованными элементами + логи выполнения

		List<String> result = List.of("a", "bb", "ccc").stream().map(x -> {
			System.out.println("name: " + x);
			return x.toUpperCase();
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.length() > 1;
		}).collect(Collectors.toList());

		System.out.println("filtered list: " + result);
		// Вывод в консоли:
		// name: a
		// filter: A
		// name: bb
		// filter: BB
		// name: ccc
		// filter: CCC
		// filtered list: [BB, CCC]
	}

	@Test
	@DisplayName("Найти самую длинную строку через Optional")
	public void testFindingLongestStringWithOptional() {
		// Тест демонстрирует поиск строки с максимальной длиной с использованием Optional
		// Результат: строка с наибольшей длиной или пустая строка, если Optional пуст

		Optional<String> longest = List.of("a", "bb", "ccc", "dddd").stream()
				.max(Comparator.comparingInt(String::length));

		System.out.println(longest.orElse(""));
		// Вывод: dddd
	}

	@Test
	@DisplayName("Посчитать сколько строк длиной больше 2-х символов")
	public void testCountingStringsLongerThanTwo() {
		// Тест демонстрирует подсчет количества строк, длина которых превышает 2 символа
		// Результат: число (long) строк, удовлетворяющих условию

		long longest =
				List.of("a", "bb", "ccc", "dddd").stream().filter(s -> s.length() > 2).count();

		System.out.println(longest);
		// Вывод: 2
	}

	@Test
	@DisplayName("Найти самую длинную строку")
	public void testFindingLongestString() {
		// Тест демонстрирует поиск строки с максимальной длиной из списка
		// Результат: строка с наибольшим количеством символов (или пустая строка, если список пуст)

		String longest = List.of("a", "bb", "ccc", "dddd").stream().max(Comparator.comparing(String::length))
				.orElse("");

		System.out.println(longest);
		// Вывод: dddd
	}

	@Test
	@DisplayName("Найти самую длинную строку")
	public void testFindingLongestString1() {
		// Тест демонстрирует поиск строки с максимальной длиной из списка
		// Результат: строка с наибольшим количеством символов (или пустая строка, если список пуст)

		//int longest = List.of("a", "bb", "ccc", "dddd").stream().map(String::length).max(Integer::compareTo).orElse(0);
		String longest = List.of("a", "bb", "ccc", "dddd").stream().max(Comparator.comparing(String::length))
				.orElse("");

		System.out.println(longest);
		// Вывод: dddd
	}

	@Test
	@DisplayName("Преобразовать список строк в список их длин")
	public void testStringToLengthMapping() {
		// Тест демонстрирует преобразование списка строк в список их длин
		// Результат: List<Integer> с длинами соответствующих строк

		List<Integer> ssa =
				List.of("dsds", "Sdsd", "sdssawer").stream().map(String::length).toList();

		System.out.println(ssa);
		// Вывод: [4, 4, 8]
	}

	@Test
	@DisplayName("Отфильтровать и получить список строк, которые содержат букву 'a'")
	public void testFilteringStringsContainingA() {
		// Тест демонстрирует фильтрацию строк, содержащих определенный символ
		// Результат: List<String> только с теми строками, которые содержат букву 'a'

		List<String> longest =
				List.of("a", "bba", "ccc", "dddda").stream().filter(s -> s.contains("a")).collect(Collectors.toList());

		System.out.println(longest);
		// Вывод: [a, bba, dddda]
	}

	@Test
	@DisplayName("Объединить все строки в одну через запятую")
	public void testStringJoiningWithComma() {
		// Тест демонстрирует объединение всех строк из списка в одну строку через запятую
		// Результат: одна строка с элементами, разделенными запятыми

		String allstring =
				List.of("dsds", "Sdsd", "sdssawer").stream().collect(Collectors.joining(","));

		System.out.println(allstring);
		// Вывод: dsds,Sdsd,sdssawer
	}

	@Test
	@DisplayName("Возвести числа в квадрат")
	public void testNumberSquaring() {
		// Тест демонстрирует возведение каждого числа из списка в квадрат
		// Результат: List<Integer> с квадратами исходных чисел

		List<Integer> ssa = List.of(3, 8, 9).stream().map(s -> s * s).collect(Collectors.toList());

		System.out.println(ssa);
		// Вывод: [9, 64, 81]
	}

	@Test
	@DisplayName("Сортировка строк по алфавиту")
	public void testStringSortingAlphabetically() {
		// Тест демонстрирует сортировку списка строк в алфавитном порядке
		// Результат: новый список со строками, отсортированными по алфавиту

		List<String> longest =
				List.of("a", "bba", "wcc", "dddda").stream().sorted().collect(Collectors.toList());

		System.out.println(longest);
		// Вывод: [a, bba, dddda, wcc]
	}

	@Test
	@DisplayName("Подсчет количества элементов в списке, начинающихся с буквы 'a'")
	public void testCountingElementsStartingWithA() {
		// Тест демонстрирует подсчет количества строк, начинающихся с определенного символа
		// Результат: число (long) элементов, удовлетворяющих условию

		long longest = List.of("a", "bba", "awcc", "dddda").stream().filter(s -> s.startsWith("a"))
				.count();

		System.out.println(longest);
		// Вывод: 2
	}

	@Test
	@DisplayName("Соединение строк из списка с разделителем")
	public void testStringJoiningWithDelimiter() {
		// Тест демонстрирует объединение строк из списка в одну строку с заданным разделителем
		// Результат: одна строка с элементами, разделенными указанным символом/строкой

		String scleika = List.of("Привет", "мир").stream().collect(Collectors.joining("O"));

		System.out.println(scleika);
		// Вывод: ПриветOмир
	}

	@Test
	@DisplayName("Группировка строк по длине с собиранием строк в списки")
	public void testStringGroupingByLength() {
		// Тест демонстрирует группировку списка строк по их длине
		// Результат: Map<Integer, List<String>> где ключ - длина строки, значение - список строк этой длины

		Map<Integer, List<String>> grouped = List.of("a", "bb", "ccc", "ooooloooo").stream()
				.collect(Collectors.groupingBy(s -> s.length()));
		System.out.println(grouped);
		// Вывод: {1=[a], 2=[bb], 3=[ccc], 9=[ooooloooo]}
	}

	@Test
	@DisplayName("Группировка строк по значению с подсчетом количества повторений каждой строки")
	public void mapIppnt() {
		Map<String, Long> grouped = List.of("a", "bb", "a", "ccc", "ooooloooo").stream()
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		System.out.println(grouped);
		//Вывод: {bb=1, a=2, ccc=1, ooooloooo=1}
	}

	@Test
	@DisplayName("Найти самое длинное слово через max()")
	public void testFindLongestWordWithMax() {
		List<String> words = Arrays.asList("кот", "собака", "слон", "бегемот", "муравей");

		Optional<String> longest = words.stream().max(Comparator.comparing(String::length));

		System.out.println(longest.orElse(""));
		// Вывод: бегемот
	}

	@Test
	@DisplayName("Найти самое длинное слово через for-each цикл")
	public void testFindLongestWordWithForEach() {
		List<String> words = Arrays.asList("кот", "собака", "слон", "бегемот", "муравей");

		String longest = "";
		for (String word : words) {
			if (word.length() > longest.length()) {
				longest = word;
			}
		}
		System.out.println(longest);
	}

	public int findSecondLargestStream1(int[] arr) {
		return Arrays.stream(arr)           // 1. Создаем IntStream из массива [12,5,8,3]
				.boxed()                    // 2. Превращаем в Stream<Integer> для сортировки
				.sorted(Collections.reverseOrder()) // 3. Сортируем по убыванию [12,8,5,3]
				.distinct()                 // 4. Убираем дубликаты (если есть)
				.skip(1)                    // 5. Пропускаем первый элемент (максимум) [8,5,3]
				.findFirst()                // 6. Берем первый оставшийся = второй максимум
				.orElseThrow(() -> new IllegalArgumentException("Недостаточно уникальных элементов")); // 7. Если нет элемента - исключение
	}

	@Test
	public void dls() {
		int[] arr = {12, 5, 8, 3};
		int result = findSecondLargestStream1(arr);
		System.out.println("Массив: " + Arrays.toString(arr) + " -> Второй максимум: " + result);
	}

	public boolean isPalindrome1(String str) {
		String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
	}

	public boolean isGalindrome(String str) {
		String revers = str.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
		return revers.equals(new StringBuilder(revers).reverse().toString());
	}

	@Test
	public void kdls() {
		//isPalindrome1("A man a plan a canal Panama");
		System.out.println(isPalindrome1("A man a plan a canal Panama"));
	}

	@Test
	public void testSumOfSquaresOfEvenNumbers() {
		// Дан список чисел. Найти сумму квадратов всех четных чисел больше 10
		long numbers = Arrays.asList(1, 12, 5, 8, 14, 21, 16, 3, 18).stream()
				.filter(s -> s % 2 == 0 && s > 10).map(s -> s * s).mapToInt(Integer::intValue).sum();
		System.out.println(numbers);
		// Ожидаемый результат: 12² + 14² + 16² + 18² = 144 + 196 + 256 + 324 = 920

		// TODO: Реализовать решение

	}

	@Test
	public void jks() {
		//Arrays.asList(2, 8, 12, 13, 7, 44, 24).stream().filter(s -> s%2==0 && s>5).sorted().forEach(System.out::println);
		List<Integer> sol =
				List.of(2, 8, 12, 13, 7, 44, 24).stream().filter(s -> s % 2 == 0 && s > 5).sorted().peek(System.out::println).toList();

	}

	@Test
	public void testGroupingByStringLength() {
		// Сгруппировать слова по их длине
		List<String> words = Arrays.asList("cat", "dog", "elephant", "rat", "mouse", "tiger");
		// Ожидаемый результат: {3=[cat, dog, rat], 5=[mouse, tiger], 8=[elephant]}

		// TODO: Реализовать решение
		Map<Integer, List<String>> result =
				words.stream().collect(Collectors.groupingBy(String::length));
		System.out.println(result);
	}

	@Test
	public void testGroupingEvenOddNumbers() {
		// Разделить числа на четные и нечетные
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// Ожидаемый результат: {false=[1,3,5,7,9], true=[2,4,6,8,10]}

		// TODO: Реализовать решение
		Map<Boolean, List<Integer>> result =
				numbers.stream().collect(Collectors.partitioningBy(s -> s % 2 == 0));
		System.out.println(result);

		// Проверки:
		// assertEquals(2, result.keySet().size());
		// assertEquals(Arrays.asList(1,3,5,7,9), result.get(false));
		// assertEquals(Arrays.asList(2,4,6,8,10), result.get(true));
	}

	@Test
	public void sertyu() {
		List<String> list = Arrays.asList("aaa", "bbbb", "ldkdkdkdk");
		list.stream().map(s -> {
			System.out.println("map");
			return s.toUpperCase();
		}).filter(s -> {
			System.out.println(" filrer " + s);
			return s.length() > 4;
		}).forEach(s -> System.out.println("s" + s));
	}

	@Test
	public void dsmjh() {
		List.of("a", "bb", "ccc").stream().map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.length() > 1;
		}).forEach(s -> System.out.println("forEach: " + s));
	}

	@Test
	public void dsolkj() {
		List<Integer> numbers = List.of(1, 22, 54, 78, 100);
		int result = Collections.max(numbers);
		//int result = 0;
		//for(int num : numbers){
		//	if(result < num){
		//		result = num;
		//	}
		//}
		System.out.println(result);
	}

	@Test
	public void dsosdfglkj() {
		List<String> ddd = List.of("ds", "ldld", "sdecgf");
		String res = "~";
		for (String str : ddd) {
			if (str.compareTo(res) < 0) {
				res = str;
			}
		}
		System.out.println(res);
	}

	public record Person(String name, int age) {
	}

	List<Person> person =
			List.of(new Person("Limba", 25), new Person("Masha", 21), new Person("Lida", 35),
					new Person("Liza", 50));
	Map<Integer, List<Person>> byAge = person.stream().collect(Collectors.groupingBy(Person::age));


	@Test
	public void kkdkdkk(){
		List<String> list = Collections.nCopies(3, "O");
		System.out.println(list);
	}
}
