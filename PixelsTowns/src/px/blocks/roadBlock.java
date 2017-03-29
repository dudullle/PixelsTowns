package px.blocks;

public class roadBlock extends blockType{
	//CLASSE ROUTE
	@Override
	boolean build() {
		this.blocId = 1;
		return false;
	}


	@Override
	boolean improve() {

		return false;
	}

	@Override
	boolean repair() {

		return false;
	}

	@Override
	void doTick() {

		
	}
	

}
