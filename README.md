# 23-1swad-kiosk

```java
//HelloWorldSwing.java
package start;

import javax.swing.*;        

public class HelloWorldSwing {
    private static void createAndShowGUI() {
	//Create and set up the window.
	JFrame frame = new JFrame("HelloWorldSwing");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//Add the ubiquitous "Hello World" label.
	JLabel label = new JLabel("Hello World");
	frame.getContentPane().add(label);

	//Display the window.
	frame.pack();
	frame.setVisible(true);
    }

    public static void main(String[] args) {
	//Schedule a job for the event-dispatching thread:
	//creating and showing this application's GUI.
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    createAndShowGUI();
		}
	});
    }
}
```

1. start 폴더 밑 HelloWorldSwing.java 파일 생성

2. 컴파일: `javac start/HelloWorldSwing.java`

	- java 설치 안 된 경우

	- 	```
		sudo apt update
		java -version
		sudo apt install default-jre
		java -version
		sudo apt install default-jdk
		java -version
		```

3. 실행: swad-kiosk 폴더에서 `java start.HelloWorldSwing`

	![aWindow](https://github.com/ywonchae1/23-1swad-kiosk/assets/79977182/7d8ca882-e52c-4db8-ba14-58175eb19705)


### 폴더 구조

```
📦 swad-kiosk
├─ README.md
└─ 📂 start
	├─ HelloWorldSwing.java
	├─ HelloWorldSwing.class
	└─ 'HelloWorldSwing$1.class'
```