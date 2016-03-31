# RXJavaDemo
创建操作有：<br>
just( ) — 将一个或多个对象转换成发射这个或这些对象的一个Observable。<br>
from( ) — 将一个Iterable, 一个Future, 或者一个数组转换成一个Observable。<br>
repeat( ) — 创建一个重复发射指定数据或数据序列的Observable。<br>
repeatWhen( ) — 创建一个重复发射指定数据或数据序列的Observable，它依赖于另一个Observable发射的数据。<br>
create( ) — 使用一个函数从头创建一个Observable。<br>
defer( ) — 只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable。<br>
range( ) — 创建一个发射指定范围的整数序列的Observable。<br>
interval( ) — 创建一个按照给定的时间间隔发射整数序列的Observable。<br>
timer( ) — 创建一个在给定的延时之后发射单个数据的Observable。<br>
empty( ) — 创建一个什么都不做直接通知完成的Observable。<br>
error( ) — 创建一个什么都不做直接通知错误的Observable。<br>
never( ) — 创建一个不发射任何数据的Observable。<br>
<br>
变换操作有：<br>
这个页面展示了创建Observable的各种方法。<br>
<br>
just( ) — 将一个或多个对象转换成发射这个或这些对象的一个Observable。<br>
from( ) — 将一个Iterable, 一个Future, 或者一个数组转换成一个Observable。<br>
repeat( ) — 创建一个重复发射指定数据或数据序列的Observable。<br>
repeatWhen( ) — 创建一个重复发射指定数据或数据序列的Observable，它依赖于另一个Observable发射的数据。<br>
create( ) — 使用一个函数从头创建一个Observable。<br>
defer( ) — 只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable。<br>
range( ) — 创建一个发射指定范围的整数序列的Observable。<br>
interval( ) — 创建一个按照给定的时间间隔发射整数序列的Observable。<br>
timer( ) — 创建一个在给定的延时之后发射单个数据的Observable。<br>
empty( ) — 创建一个什么都不做直接通知完成的Observable。<br>
error( ) — 创建一个什么都不做直接通知错误的Observable。<br>
never( ) — 创建一个不发射任何数据的Observable。<br>
<br>
过滤操作有：<br>
map( ) — 对序列的每一项都应用一个函数来变换Observable发射的数据序列。<br>
flatMap( ), concatMap( ), and flatMapIterable( ) — 将Observable发射的数据集合变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable。<br>
switchMap( ) — 将Observable发射的数据集合变换为Observables集合，然后只发射这些Observables最近发射的数据。<br>
scan( ) — 对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值。<br>
groupBy( ) — 将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据。<br>
buffer( ) — 它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个。<br>
window( ) — 定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项。<br>
cast( ) — 在发射之前强制将Observable发射的所有数据转换为指定类型。<br>
<br>
异步的有：<br>
<br>
Schedulers.immediate():直接在当前线程运行，相当于不知道线程，这是默认的Scheduler。<br>
Schedulers.newThread():总是启用新线程，并在新线程执行操作。<br>
Schedulers.io()：I/O操作(读写文件，读写数据库，网络信息交互等)所使用的Scheduler。行为模式和newThread()差不多，区别在于io()的内部实现的是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下io()比newThread()效率更高。<br>
Shedulers.computation():计算所使用的Scheduler。这个计算指的是cpu密集型计算，例如图形的计算这个Scheduler使用的固定的线程池，大小为cpu核数，不要把I/o操作放入，会浪费cpu的。<br>
AndroidSchedulers.mainThread(),它指定的操作将在Android主线程运行。<br>
subscribeOn():指定subscribe()所发生的线程，即Observable.OnSubscribe被激活时所处的线程，或者叫做时间产生的线程。<br>
observeOn():指定subscriber所运行在的线程。或者叫做时间消费的线程。<br>
<br>
以及Retrofit2.0网络解析以及与rxjava一起使用的网络解析。
