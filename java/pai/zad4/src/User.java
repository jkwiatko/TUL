package zad4;

import java.util.Objects;

class User{
		String name;
		
		User(String theName){
			name = theName;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			
			if (!(obj instanceof User)) {
				return false;
			}

			User c = (User) obj;
			return this.name.equals(c.name);
		}

		@Override
		public int hashCode() {
		return Objects.hashCode(this.name);
		}
	}