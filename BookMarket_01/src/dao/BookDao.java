package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dto.Book;


public class BookDao implements IBookDao{
	
		private Connection conn;

	private static BookDao instance;

	public static BookDao getInstance() {
		if (instance == null)
			instance = new BookDao();
		return instance;
	}   

	private BookDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmarket_db", "root", "mysql");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> selectAllBookList() {
		// TODO Auto-generated method stub
		
	    List<Book> bookList = new ArrayList<Book>();
	    Book book = null;
	
		String sql = "SELECT * from book;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublished_date(rs.getString("published_date"));
				book.setDescription(rs.getString("description"));
				book.setGenre(rs.getInt("genre"));
				book.setBook_condition(rs.getInt("book_condition"));
				book.setIsSold(rs.getInt("isSold"));
				book.setPrice(rs.getInt("price"));
				book.setSeller(rs.getInt("seller_id"));
				book.setImage(rs.getString("image"));
				book.setSubmit_date(rs.getDate("submit_date"));
				book.setPrice_type(rs.getInt("price_type"));
				book.setComment(rs.getString("comment"));
				book.setComment_img(rs.getString("comment_img"));
				bookList.add(book);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}

	@Override
	public Book getBook(int book_id) {

	    Book book = null;
		String sql = "select * from book where book_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,book_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublished_date(rs.getString("published_date"));
				book.setDescription(rs.getString("description"));
				book.setGenre(rs.getInt("genre"));
				book.setBook_condition(rs.getInt("book_condition"));
				book.setIsSold(rs.getInt("isSold"));
				book.setPrice(rs.getInt("price"));
				book.setSeller(rs.getInt("seller_id"));
				book.setImage(rs.getString("image"));
				book.setSubmit_date(rs.getDate("submit_date"));
				book.setPrice_type(rs.getInt("price_type"));
				book.setComment(rs.getString("comment"));
				book.setComment_img(rs.getString("comment_img"));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return book;
	}

	@Override
	public int insertBook(Book book) {
		String sql = "INSERT INTO BOOK VALUES(0,?,?,?,?,?,?,?,?,?,?,?,?,sysdate(),?,?,?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getIsbn());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getTitle());
			pstmt.setString(4,book.getPublisher());
			pstmt.setString(5,book.getPublished_date());
			pstmt.setString(6,book.getDescription());
			pstmt.setInt(7, book.getGenre());
			pstmt.setInt(8, book.getBook_condition());
			pstmt.setInt(9, book.getIsSold());
			pstmt.setInt(10, book.getPrice());
			pstmt.setInt(11,book.getSeller());
			pstmt.setString(12, book.getImage());
			pstmt.setInt(13, book.getPrice_type());
			pstmt.setString(14, book.getComment());
			pstmt.setString(15, book.getComment_img());
		
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int updateBook(Book book) {
		String sql = "UPDATE BOOK SET isSold = ?, price= ?, comment= ?, comment_img= ? where book_id = ? ";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book.getIsSold());
			pstmt.setInt(2, book.getPrice());
			pstmt.setString(3, book.getComment());
			pstmt.setString(4, book.getComment_img());
			pstmt.setInt(5,	book.getBook_id());
		
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

	@Override
	public int deleteBook(int book_id) {
		PreparedStatement pstmt = null;
		String sql = "delete book where book_id= ?";
		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}


	@Override 
	public List<Book> selectBookByGenre(int genre) {
		List<Book> bookList = new ArrayList<Book>();
	    Book book = null;
		String sql = "SELECT * FROM book where genre= ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, genre);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublished_date(rs.getString("published_date"));
				book.setDescription(rs.getString("description"));
				book.setGenre(rs.getInt("genre"));
				book.setBook_condition(rs.getInt("book_condition"));
				book.setIsSold(rs.getInt("isSold"));
				book.setPrice(rs.getInt("price"));
				book.setSeller(rs.getInt("seller_id"));
				book.setImage(rs.getString("image"));
				book.setSubmit_date(rs.getDate("submit_date"));
				book.setPrice_type(rs.getInt("price_type"));
				book.setComment(rs.getString("comment"));
				book.setComment_img(rs.getString("comment_img"));
				
				bookList.add(book);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}

	@Override
	public List<Book> selectBookByTitle(String title) {
		List<Book> bookList = new ArrayList<Book>();
	    Book book = null;
		String sql = "SELECT * FROM book where title= ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublished_date(rs.getString("published_date"));
				book.setDescription(rs.getString("description"));
				book.setGenre(rs.getInt("genre"));
				book.setBook_condition(rs.getInt("book_condition"));
				book.setIsSold(rs.getInt("isSold"));
				book.setPrice(rs.getInt("price"));
				book.setSeller(rs.getInt("seller_id"));
				book.setImage(rs.getString("image"));
				book.setSubmit_date(rs.getDate("submit_date"));
				book.setPrice_type(rs.getInt("price_type"));
				book.setComment(rs.getString("comment"));
				book.setComment_img(rs.getString("comment_img"));
				
				bookList.add(book);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}

	@Override
	public List<Book> selectBookByAuthor(String author) {
		List<Book> bookList = new ArrayList<Book>();
	    Book book = null;
		String sql = "SELECT * FROM book where author= ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, author);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublished_date(rs.getString("published_date"));
				book.setDescription(rs.getString("description"));
				book.setGenre(rs.getInt("genre"));
				book.setBook_condition(rs.getInt("book_condition"));
				book.setIsSold(rs.getInt("isSold"));
				book.setPrice(rs.getInt("price"));
				book.setSeller(rs.getInt("seller_id"));
				book.setImage(rs.getString("image"));
				book.setSubmit_date(rs.getDate("submit_date"));
				book.setPrice_type(rs.getInt("price_type"));
				book.setComment(rs.getString("comment"));
				book.setComment_img(rs.getString("comment_img"));
				
				bookList.add(book);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}

	@Override
	public List<Book> selectBookByPublisher(String publisher) {
		List<Book> bookList = new ArrayList<Book>();
	    Book book = null;
		String sql = "SELECT * FROM book where publisher= ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, publisher);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublished_date(rs.getString("published_date"));
				book.setDescription(rs.getString("description"));
				book.setGenre(rs.getInt("genre"));
				book.setBook_condition(rs.getInt("book_condition"));
				book.setIsSold(rs.getInt("isSold"));
				book.setPrice(rs.getInt("price"));
				book.setSeller(rs.getInt("seller_id"));
				book.setImage(rs.getString("image"));
				book.setSubmit_date(rs.getDate("submit_date"));
				book.setPrice_type(rs.getInt("price_type"));
				book.setComment(rs.getString("comment"));
				book.setComment_img(rs.getString("comment_img"));
				
				bookList.add(book);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}

	@Override
	public List<Book> selectBookBySeller(int seller) {
		List<Book> bookList = new ArrayList<Book>();
	    Book book = null;
		String sql = "SELECT * FROM book where seller_id= ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seller);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublished_date(rs.getString("published_date"));
				book.setDescription(rs.getString("description"));
				book.setGenre(rs.getInt("genre"));
				book.setBook_condition(rs.getInt("book_condition"));
				book.setIsSold(rs.getInt("isSold"));
				book.setPrice(rs.getInt("price"));
				book.setSeller(rs.getInt("seller_id"));
				book.setImage(rs.getString("image"));
				book.setSubmit_date(rs.getDate("submit_date"));
				book.setPrice_type(rs.getInt("price_type"));
				book.setComment(rs.getString("comment"));
				book.setComment_img(rs.getString("comment_img"));
				
				bookList.add(book);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}

	@Override
	//genre 내에서 order 기준으로  seq(desc, asc)로 정렬되는 메소드 
	public List<Book> selectBookOrderBy(int genre, String order, String seq) {
		//book_list만들고 채울게요
		return null;
		
	}

	@Override
	//genre 내에서 price_type으로 정렬
	public List<Book> selectBookByPriceType(int genre, int price_type) {
		List<Book> bookList = new ArrayList<Book>();
	    Book book = null;
		String sql = "SELECT * FROM book where genre= ? and price_type = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, genre);
			pstmt.setInt(2, price_type);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublished_date(rs.getString("published_date"));
				book.setDescription(rs.getString("description"));
				book.setGenre(rs.getInt("genre"));
				book.setBook_condition(rs.getInt("book_condition"));
				book.setIsSold(rs.getInt("isSold"));
				book.setPrice(rs.getInt("price"));
				book.setSeller(rs.getInt("seller_id"));
				book.setImage(rs.getString("image"));
				book.setSubmit_date(rs.getDate("submit_date"));
				book.setPrice_type(rs.getInt("price_type"));
				book.setComment(rs.getString("comment"));
				book.setComment_img(rs.getString("comment_img"));
				
				bookList.add(book);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}
	@Override
	public List<Book> selectBookByCondition(int genre, int book_condition) {
		List<Book> bookList = new ArrayList<Book>();
	    Book book = null;
		String sql = "SELECT * FROM book where genre= ? and book_condition = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, genre);
			pstmt.setInt(2, book_condition);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublished_date(rs.getString("published_date"));
				book.setDescription(rs.getString("description"));
				book.setGenre(rs.getInt("genre"));
				book.setBook_condition(rs.getInt("book_condition"));
				book.setIsSold(rs.getInt("isSold"));
				book.setPrice(rs.getInt("price"));
				book.setSeller(rs.getInt("seller_id"));
				book.setImage(rs.getString("image"));
				book.setSubmit_date(rs.getDate("submit_date"));
				book.setPrice_type(rs.getInt("price_type"));
				book.setComment(rs.getString("comment"));
				book.setComment_img(rs.getString("comment_img"));
				
				bookList.add(book);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}
	

}
