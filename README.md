# web-donation-spring-boot

Dự án là một website quyên góp từ thiện đưa ra thông tin các đợt quyên góp từ thiện để các nhà hảo tâm có thể tiến hành quyên góp. 
Đi kèm với đó là mô tả chi tiết đợt từ thiện và thông tin về số tiền từ thiện sẽ công khai trên website.

Sử dụng: Spring Boot, Spring MVC, Psring Security, Spring Data JPA, SQL server

Chức năng của website:
1. Chức năng của admin
   
1.1 Hiển thị ra danh sách của người dùng và thêm, sửa, xóa người dùng

![ASM1_danhsachnguoidung](https://github.com/tuanhung96/web-donation/assets/113849269/8843909d-de33-45af-8e12-2d86f0333b56)
- Vai trò: sẽ tồn tại 2 vai trò chính trong hệ thống là: người quản lý hệ thống (Admin) và người dùng cơ bản (User)
- Trạng thái: Có hai trạng thái cơ bản trong hệ thống là: Hoạt động và Đã khóa.
Ở trạng thái “Hoạt động” thì người dùng có thể sử dụng hệ thống cơ bản.
Ở trạng thái “Đã khóa” thì người dùng không sử dụng những chức năng cơ bản của hệ thống.
- Hành động: Mô tả các hành động (nút ấn) của hệ thống như: Sửa (cập nhật thông tin), Chi tiết, Xóa, Khóa
1.2 Tìm thông tin người dùng qua số điện thoại hoặc email
1.3 Cho phép/không cho phép một người dùng sử dụng hệ thống
- Ở trạng thái “Khóa” thì người dùng được chuyển từ trạng thái “Hoạt động” sang trạng thái “Đã khóa” tương ứng.
Người dùng sẽ không thể truy cập vào hệ thống và thực hiện được chức năng tương ứng.
- Ở trạng thái “Mở” thì người dùng sẽ được chuyển từ trạng thái “Đã khóa” sang trạng thái “Hoạt động” tương ứng.

1.4 Quản lý đợt quyên góp
- Hiển thị ra danh sách của đợt quyên góp
  
![ASM1_danhsachdotquyengop](https://github.com/tuanhung96/web-donation/assets/113849269/e7dad6ad-73c3-42d2-8880-561e6d970f5a)
- Thêm, sửa, xóa, tìm kiếm đợt quyên góp
- Thay đổi trạng thái của một đợt quyên góp
  + Trạng thái mới tạo: Đợt quyên góp vừa mới được khởi tạo chưa bắt đầu chạy quyên góp. Người dùng hệ thống sẽ khởi tạo đợt quyên góp.
  + Đang quyên góp: Đợt quyên góp đang được quyên góp. Người dùng hệ thống có thể tham gia đợt quyên góp này.
  + Kết thúc đợt quyên góp: Đợt quyên góp hoàn thành mục tiêu, có thể là về thời gian hoặc số tiền cần quyên góp. 
  + Đóng quyên góp: Đóng đợt quyên góp, không ai có thể tham gia quyên góp được nữa và trạng thái của đợt quyên góp sẽ KHÔNG thể thay đổi được.

1.5 Xác nhận/hủy xác nhận với việc nhận được tiền từ nhà quyên góp

![ASM1_xacnhanquyengop](https://github.com/tuanhung96/web-donation/assets/113849269/17bd5b5c-96bf-416c-b0f5-aed5e3e3dc94)

2. Chức năng dành cho user
- Hiển thị danh sách đợt quyên góp
- Xem thông tin chi tiết đợt quyên góp
- Thực hiện quyên góp
