package application;

import java.lang.reflect.Field;

public class PrintingFunctionModuleManagement {

	public static void printDataClassGettersAndSetters(Object instance) {
		Field f[] = (instance.getClass().getDeclaredFields());

		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			StringBuilder sb = new StringBuilder();
			char varChar[] = varName.toCharArray();
			// generate setter
			varChar[0] = Character.toUpperCase(varChar[0]);
			sb.append("public void ");
			sb.append("set");
			sb.append(varChar);
			sb.append("(");
			sb.append(varType);
			sb.append(" ");
			sb.append(varName);
			sb.append(")");
			sb.append(" ");
			sb.append("{");
			sb.append("\n");
			sb.append("\t");
			sb.append("this.");
			sb.append(varName);
			sb.append(" = ");
			sb.append(varName);
			sb.append(";\n");
			sb.append("}");
			
			System.out.println(sb.toString());
		}
	}

}
