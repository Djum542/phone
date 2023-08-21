# PhoneZTest

# Some Images from website

- Home page:
![image](https://github.com/TrongBui15012002/PhoneZTest/assets/91036603/875a1740-3a08-4dd4-8623-18e1cf3c157b)

- Products:
![image](https://github.com/TrongBui15012002/PhoneZTest/assets/91036603/5590fd4d-306c-4ed5-ade1-d1cda9819925)
  [image](https://github.com/TrongBui15012002/PhoneZTest/assets/91036603/2b390069-4198-4a58-8436-928402482308)

- Cart:
  ![image](https://github.com/TrongBui15012002/PhoneZTest/assets/91036603/032d33a8-660c-4b8d-ba74-4fdd256ba5f0)

- Admin dashboard:
  ![image](https://github.com/TrongBui15012002/PhoneZTest/assets/91036603/721c6d66-187d-4fb0-8c62-462ec15d9c63)

- Authenticate:
![image](https://github.com/TrongBui15012002/PhoneZTest/assets/91036603/f786bb88-e949-4475-afb8-199fb0ef0c06)
![image](https://github.com/TrongBui15012002/PhoneZTest/assets/91036603/2b390069-4198-4a58-8436-928402482308)

- Cart:
![image](https://github.com/TrongBui15012002/PhoneZTest/assets/91036603/032d33a8-660c-4b8d-ba74-4fdd256ba5f0)

- Admin dashboard:
![image](https://github.com/TrongBui15012002/PhoneZTest/assets/91036603/721c6d66-187d-4fb0-8c62-462ec15d9c63)

Cách cài đặt trang web
1. có môi trường JDK của java nếu chưa có thì thì có thể tải tại trang web này:

https://www.oracle.com/java/technologies/downloads/#jdk17-windows
chọn phiên bản phù hợp với máy mà bạn đang dùng.
Hướng dẫn cấu hình môi trường cho java.https://freetuts.net/thu-thuat/huong-dan-cau-hinh-va-cai-dat-java-2016t.html
Kiểm tra bằng command prompt với câu lệnh java --version
2. cài đặt môi trường maven để cài đặt maven trước tiên bạn cần tải gói maven phù hợp:https://maven.apache.org/download.cgi
   Hướng dẫn cấu hình maven: https://stackjava.com/install/maven-phan-1-maven-la-gi-cai-dat-maven.html
   Kiểm tra xem đã cài đặt maven thành công hay chưa ta chỉ cần mở command prompt và gõ vào mvn --version

3. Cài đặt trình biên dịch như vscode, intellij IDE, eclip,...
4. Cài đặt mysql
   Link tải: https://dev.mysql.com/downloads/mysql/
   Hướng dẫn cài: https://bizflycloud.vn/tin-tuc/huong-dan-cai-dat-mysql-tren-he-dieu-hanh-windows-don-gian-nhat-20210407181410254.htm
5. cấu hình lại file application.properties
   logging.level.org.hibernate.SQL=DEBUG
   logging.level.org.hibernate.type=TRACE
   spring.security.user.name=djum44@gmail.com // có thể thay đổi
   spring.security.user.password=15142 // có thể thay đổi
   server.port=8085
   app.jwt.secret=abcdefghijklmnOPQRSTUVWXYZ
   spring.datasource.url=jdbc:mysql://localhost:4420/shopdb // đổi lại port 4420 cho đúng với port của mysql của bạn và thường port mac đinh là 3306 , đổi 'shopdb' thành database của bạn
   spring.datasource.username=root // có thể thay đổi theo tên đăng nhập và mật khẩu để trùng với tài khoản mật khẩu bạn thiết lập cho mysql khi bạn cài đặt.
   spring.datasource.password=15142 // có thể thay đổi
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.format_sql=true
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
4. mở gói lên và tìm đến file ShopProjectApplication.java và run chương trình.

