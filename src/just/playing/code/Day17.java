package just.playing.code;

import java.util.HashMap;
import java.util.Map;

public class Day17 {

	private static int[] containers = new int[] { 33, 14, 18, 20, 45, 35, 16,
			35, 1, 13, 18, 13, 50, 44, 48, 6, 24, 41, 30, 42 };
	private static Map<Integer, Integer> howManyWaysWithConstantNumberOfContainers = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		init();
		int rez = 0;
		for (int i1 = 0; i1 < 2; i1++)
			for (int i2 = 0; i2 < 2; i2++)
				for (int i3 = 0; i3 < 2; i3++)
					for (int i4 = 0; i4 < 2; i4++)
						for (int i5 = 0; i5 < 2; i5++)
							for (int i6 = 0; i6 < 2; i6++)
								for (int i7 = 0; i7 < 2; i7++)
									for (int i8 = 0; i8 < 2; i8++)
										for (int i9 = 0; i9 < 2; i9++)
											for (int i10 = 0; i10 < 2; i10++)
												for (int i11 = 0; i11 < 2; i11++)
													for (int i12 = 0; i12 < 2; i12++)
														for (int i13 = 0; i13 < 2; i13++)
															for (int i14 = 0; i14 < 2; i14++)
																for (int i15 = 0; i15 < 2; i15++)
																	for (int i16 = 0; i16 < 2; i16++)
																		for (int i17 = 0; i17 < 2; i17++)
																			for (int i18 = 0; i18 < 2; i18++)
																				for (int i19 = 0; i19 < 2; i19++)
																					for (int i20 = 0; i20 < 2; i20++) {
																						int sum = i1
																								* containers[0]
																								+ i2
																								* containers[1]
																								+ i3
																								* containers[2]
																								+ i4
																								* containers[3]
																								+ i5
																								* containers[4]
																								+ i6
																								* containers[5]
																								+ i7
																								* containers[6]
																								+ i8
																								* containers[7]
																								+ i9
																								* containers[8]
																								+ i10
																								* containers[9]
																								+ i11
																								* containers[10]
																								+ i12
																								* containers[11]
																								+ i13
																								* containers[12]
																								+ i14
																								* containers[13]
																								+ i15
																								* containers[14]
																								+ i16
																								* containers[15]
																								+ i17
																								* containers[16]
																								+ i18
																								* containers[17]
																								+ i19
																								* containers[18]
																								+ i20
																								* containers[19];
																						System.out
																								.println("sum: "
																										+ sum);
																						if (150 == sum) {
																							rez++;
																							int numberOfContainers = i1
																									+ i2
																									+ i3
																									+ i4
																									+ i5
																									+ i6
																									+ i7
																									+ i8
																									+ i9
																									+ i10
																									+ i11
																									+ i12
																									+ i13
																									+ i14
																									+ i15
																									+ i16
																									+ i17
																									+ i18
																									+ i19
																									+ i20;
																							howManyWaysWithConstantNumberOfContainers.put(numberOfContainers, howManyWaysWithConstantNumberOfContainers.get(numberOfContainers) + 1);
																						}
																					}
		System.out.println("possible ways: " + rez);
		System.out.println("----");
		for(Integer i : howManyWaysWithConstantNumberOfContainers.keySet()) {
			System.out.println(i + ": " + howManyWaysWithConstantNumberOfContainers.get(i));
		}
	}

	private static void init() {
		for (int i = 1; i < 21; i++) {
			howManyWaysWithConstantNumberOfContainers.put(i, 0);
		}
	}
}
