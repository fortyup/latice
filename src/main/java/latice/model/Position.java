package latice.model;

public final class Position {

	private final Integer column;
	private final Integer row;

	public Position(Integer column, Integer row) {
		this.column = column;
		this.row = row;
	}

	public Integer row() {
		return row;
	}

	public Integer column() {
		return column;
	}

	@Override
	public String toString() {
		return row.toString() + "," + column.toString();
	}

}
