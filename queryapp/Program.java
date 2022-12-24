package queryapp;

import java.util.Scanner;

public class Program
{
	public static void main(String[] args)
	{
			char choice = 'y';
			Scanner scanner = new Scanner(System.in);
			while (choice != 'n' || choice != 'N')
			{
						try
						{
							SQLQueries user = new SQLQueries();
							System.out.println("Menu:");
							System.out.println("1. Register User");
							System.out.println("2. Delete User");
							System.out.println("3. Change Password");
							System.out.println("4. Show user details");
							System.out.println("5. Update User");
							System.out.println("6. Exit");
							int options = scanner.nextInt();
							switch(options) {
							case 1:
								System.out.println("Enter UserName");
								String uName = scanner.next();
								System.out.println("Enter Password");
								String password1 = scanner.next();
								System.out.println("Enter Name");
								String name = scanner.next();
								System.out.println("Enter Email");
								String email = scanner.next();
								user.Insert(uName,password1,name,email);
								System.out.println("User Registered");
								System.out.println();
								break;
							case 2:
								System.out.println("Enter UserName");
								String uNameDelete = scanner.next();
								user.delete(uNameDelete);
								System.out.println("User "+uNameDelete+" Deleted");
								System.out.println();
								break;
							case 3:
								System.out.println("Enter UserName");
								String uNamePassword = scanner.next();
								System.out.println("Enter New Password");
								String password2 = scanner.next();
								user.changePassword(uNamePassword,password2);
								System.out.println("Password of "+uNamePassword+" changed to "+password2);
								System.out.println();
								break;
							case 4:
								System.out.println("Enter UserName");
								String uNameShow = scanner.next();
								user.showDetails(uNameShow);
								System.out.println();
								break;
							case 5:
								System.out.println("Enter Old Username");
								String oldUName = scanner.next();
								System.out.println("Enter New UserName");
								String uNameNew = scanner.next();
								System.out.println("Enter New Password");
								String passwordNew = scanner.next();
								System.out.println("Enter New Name");
								String nameNew = scanner.next();
								System.out.println("Enter New Email");
								String emailNew = scanner.next();
								user.updateDetails(oldUName,uNameNew,passwordNew,nameNew,emailNew);
								System.out.println("Updated details of user "+oldUName);
								System.out.println();
								break;
							case 6:
								options=6;
								System.out.println("Have a Good Day!");
								break;
							default:
								System.out.println("Invalid Choice.");
								break;
							}
						} catch (Exception e)
						{
						}
						System.out.println("Do you want to Continue(Y/N)?");
						choice = scanner.next().charAt(0);
			}
			if (scanner!=null)
			{
				scanner.close();
			}
	}

}
