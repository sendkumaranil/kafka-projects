# microservices communicates each other through kafka
Kafka Brokers, Order-Service, Shipment-service, Invoice-service
<hr/>
<h5> Download Kafka here: <a href="https://kafka.apache.org/downloads">download</a></h5>
<hr/>
<h4> Start Zookeeper:</h4>
<p>Kafka uses ZooKeeper so you need to first start a ZooKeeper server if you don't already have one. You can use the convenience script packaged with kafka to get a quick-and-dirty single-node ZooKeeper instance.</p>
      <span>
          > bin/zookeeper-server-start.sh ../config/zookeeper.properties
      </span>
<hr/>
<h4> Start Server:</h4>
<p>Open new terminal and run below command:</p>
<span>
      > bin/kafka-server-start.sh config/server.properties
</span>
<hr/>
<h4> Create Topic:</h4>
<p>Open new terminal and run below command:</p>
<span>
      > bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic order
</span>
<hr/>
<h4> Start Producer Spring boot project: order-service</h4>
<h4> Start Consumer Spring boot project: shipping-service </h4>
<h4> Start Consumer Spring boot project: invoicing-service </h4>

<h4> Send Message</h4>
<p>Order-Service will send order creation event to the Kafka topic:: order</p>
<h4> Recieve Message</h4>
<p>Shipping-Service will recieve order creation event from the Kafka topic:: order and store the data to shipping database</p>
<p>Invoicing-Service will recieve order creation event from the Kafka topic:: order and store data to invoice database</p>

<hr/>
