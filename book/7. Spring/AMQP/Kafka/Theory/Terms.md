# Basic Terms (Основные термины)

1. [Cluster](#1-cluster)
2. [Node](#2-node)
3. [Kafka](#3-kafka)
4. [Partition](#4-partition)
5. [Message](#5-record--message-)
6. [Zookeeper](#6-zookeeper)

### 1. Cluster
Кластер - Несколько экземпляров
Cluster - Multiple instances

![Cluster_1.png](img%2FCluster_1.png)

### 2. Node

![Node_1.png](img%2FNode_1.png)

### 3. Kafka
![Kafka.png](img%2FKafka.png)

- **Producer** - пишет сообщения в Kafka (writes messages to Kafka)
- **Consumer** - читает сообщения из Kafka (reads messages from Kafka)
- **Topic(Тема)** - Kafka topics are the categories used to organize messages. Это не физический уровень а логический.
Each topic has a name that is unique across the entire Kafka cluster.
- **Record** - сообщение

Например (for instance): order_topic, user_topic ...

### 4. Partition

![Partitions.png](img%2FPartitions.png)
The partitions are important because they enable parallelization of topics.

Разделы важны, потому что они позволяют распараллелить темы.

![anatomu_of_topics.png](img%2Fanatomu_of_topics.png)

- Топик делится на Partitions
- Каждый partitions сохраняет порядок
- Каждое сообщение в partitions получает номер или offset

### 5. Record(message)
![Record.png](img%2FRecord.png)
- key
- value
- timestamp

### 6. Zookeeper
Для Kafka по сути(in fact) база данных. 
Нужна, что бы обеспечить максимальную скорость чтения и не очень высокую скорость записи.
Kafka хранит в ней конфигурационные настройки.

![Zookeeper.png](img%2FZookeeper.png)
