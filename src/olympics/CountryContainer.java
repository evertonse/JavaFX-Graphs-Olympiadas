
package olympics;


//  NOT-GENERIC  Data structure for unique countries
// Array dinamico para Paises, e funções auxiliares
class CountryContainer
{
	private Country[]  _buffer  = null;
	private int _capacity = 0;
	public int length = 0;
	
	// Start the Capacity at 25;
	CountryContainer()
	{
		int start_size = 25;
		
		_SetCapacity(start_size);
		this.length = 0;
		_buffer = new Country[_capacity];
	}
	
	// Start the Capacity at the user's will
	CountryContainer(int start_size)
	{
		_SetCapacity(start_size);
		this.length = 0;
		_buffer = new Country[_capacity];
	}

	// Adiciona um pais a estrutura
	public Country 
	Append(Country item)
	{
		Country return_item;
		if (length >= _capacity)					// if our index is bigger than our array capacity,
		{													// we grow our buffer array 2x the old capacity
			_GrowBuffer(_capacity * 2);
		}

		int index_found = _Find(item.name);

		// If there ISN'T a country with the same name
		if(index_found < 0)
		{
			this._buffer[length] = item;
			// returns the item that changed
			return_item = this._buffer[length];
			// We increase the length count
			this.length++;
		}

		// If there IS a country with the same name
		else
		{
			_Merge(this._buffer[index_found],item);
			
			// returns the item that changed
			return_item  = this._buffer[index_found];
		}
		// which item foes into 'return_item' should be decided befor this line
		return return_item;
	}

	// Simply return the Item, could return an item outside bounds, 
	// but JAVA will probably throw a nullexpection
	public Country 
	Get(int i)
	{
		if (i == -1) return _buffer[length-1];
		if (i > this._capacity - 1)
		{
			System.out.println("[WARNING] Acessing things you shouldn't !");
		}
		return _buffer[i];
	}

	// Returns a deepcopy of this._buffer
	public Country[]
	ToArray()
	{
		return _DeepCopyBuffer();
	}

	// Seta a capacidade
	private void 
	_SetCapacity(int newSize)
	{
		this._capacity= newSize;
	}
	
	// Makes a Deep Copy os this container
	private Country[] 
	_DeepCopyBuffer()
	{
		Country copied_buffer[] = new Country[this.length];
		
		for (int i = 0; i < copied_buffer.length; i++)
		{
			Country curr_obj = this._buffer[i];
			copied_buffer[i] =  new Country(curr_obj.name);

			_Merge(copied_buffer[i] , curr_obj);
		}
		return copied_buffer;
	}

	private void 
	_GrowBuffer(int newSize)
	{
		Country newBuffer[] = new Country[newSize];

		// Copying content to new buffer
		for (int i = 0; i < this._capacity ; i++)
		{
			newBuffer[i] = _buffer[i];
		}
		// Clean old and Assign new to this;
		this._buffer = newBuffer;
		_SetCapacity(newSize);
	}

	// NOTE(Everton): Return the index of a item found. 
	// if it can't find it, returns -1;
	private int
   _Find(String name)
	{
		if (name == null)
		{
			System.out.print("[[WTF NAME IS NULL]]");
		}
		try
		{
			for (int i = 0; i < this.length; i++) 
			{

				if(this._buffer[i] == null)
					continue;
				else
				{
					if(name.equals(this._buffer[i].name))
					{
						return i;
					}	
				}
			} 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		return -1;
	}
	
	// Junta dois paises
	private void
   _Merge(Country destination , Country source)
	{
		for (int i = 0; i < source.ouro; i++) 
		{
			destination.AddMedal("ouro");
		}
		for (int i = 0; i < source.prata; i++) 
		{
			destination.AddMedal("prata");
		}
		for (int i = 0; i < source.bronze; i++) 
		{
			destination.AddMedal("bronze");
		}
	}
	
	// Sort Countries based on their medals, Gold being the most important, followed by silver then bronze
	static public void
	BubbleSortCountries(Country arr[], int size)
   {
   	int n = arr.length;

   	for (int i = 0; i < arr.length; i++) 
		{
        boolean already_sorted = true;
        for (int j = 0; j < n - i - 1; j++)
			{		  
            if(arr[j].ouro < arr[j + 1].ouro)
				{	// Swap Elements
					Country temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1]= temp;
					already_sorted = false;
				}
            else if( arr[j].ouro == arr[j + 1].ouro)
				{
					if(arr[j].prata < arr[j + 1].prata)
					{
						// Swap Elements
						Country temp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1]= temp;
						already_sorted = false;
					}

					else if( arr[j].prata == arr[j + 1].prata)
					{
						if(arr[j].bronze < arr[j + 1].bronze)
						{	
							// Swap Elements
							Country temp = arr[j];
							arr[j] = arr[j+1];
							arr[j+1]= temp;
							already_sorted = false;
						}
					}
				}
			}
      	if(already_sorted)
		  		break;
		}
	}
}