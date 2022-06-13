service name = price
module = price
parent= neoServices
dependency with other module = product;

package= com.mehbub.price
Primary= --server.port: 8082
Secondary= --server.port=8028
application name= product
datasource url = jdbc:postgresql://localhost:5432/price
eureka client = yes
register-with-eureka: true