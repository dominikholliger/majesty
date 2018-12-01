package leberkaes.commonClasses;




public class GameMsg extends Message {
	private String name;
	private String content;
	
	public GameMsg(String name, String content) {
		super(MessageType.Game);
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return type.toString() + '|' + name + '|' + sanitize(content);
	}
	
	private String sanitize(String in) {
		return in.replace('|', '/');
	}
}



