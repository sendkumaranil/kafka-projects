# kafka-projects
Kafka Brokers, Kafka-Consumer, Kafka-Producer, Secure Kafka endpoint with Okta Oauth2.0
<hr/>
<h5> Download Kafka here: <a href="https://kafka.apache.org/downloads">download</a></h5>
<hr/>
# start the servers:
<h4> Start Zookeeper:</h4>
<p>Kafka uses ZooKeeper so you need to first start a ZooKeeper server if you don't already have one. You can use the convenience script packaged with kafka to get a quick-and-dirty single-node ZooKeeper instance.</p>
      <span>
          > bin/zookeeper-server-start.sh ../config/zookeeper.properties  
      </span>
