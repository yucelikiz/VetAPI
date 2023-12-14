# VetAPI Veteriner Randevu Takip Sistemi

Bu proje, veteriner randevularını yönetmek ve takip etmek amacıyla geliştirilmiş bir sistemdir.

## Sınıflar
- Customer
- Doctor
- Animal
- Vaccine
- Appointment
- AvailableDate

## Kullanılan Teknolojiler

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/download.cgi)
- [JAva](https://www.oracle.com/java/technologies/downloads/)
- [Postgre SQL](https://www.postgresql.org/download/)

## Başlangıç

Bu talimatlar, projenin bilgisayarınızda nasıl çalıştırılacağını anlatır.

### Kurulum
1. Repoyu bilgisayarınıza klonlayın
   
`git clone https://github.com/kullanici/veteriner-randevu-sistemi.git`

2. Proje dizinine gidin

`cd veteriner-randevu-sistemi`

3. `application.properties` dosyasını düzenleyin ve PostgreSQL bağlantı bilgilerinizi ekleyin
```
spring.datasource.url=jdbc:postgresql://localhost:5432/vet
spring.datasource.username=postgre
spring.datasource.password=1234

```
