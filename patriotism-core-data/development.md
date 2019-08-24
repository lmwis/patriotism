> Date: 2019.8.19 
> Auth: Nightnessss

1.  行为：增加`vo.CommentDisplayInfo`类的`id`字段

    目的：用与对于评论进行点赞时对评论进行查找
    
    衍生操作：在`CommentServiceImpl.selectCommentByActualIdAndDataType`方法中增加`commentDisplayInfo.setId(k.getId());`

> Date: 2019.8.20
> Auth: Nightnessss


1.  行为：增加`vo.CommentDisplayInfo`类的`like`字段

    目的：用于在前端判断当前用户是否已对该评论点赞
    
    衍生操作：在`DataController`增加传参`user_id`,对应对接口`CommentService`也增加该参数

2.  行为：对全体`controller`传进来对字符串参数进行判空

    目的：减少未知错误
    
    衍生操作：无
    
3.  行为：注释`IndexController`中对接口

    目的：使`swagger`更美观
    
    衍生操作：无
    
4.  行为：对全体`controller`传参打印在日志上

    目的：使后台更全面地监控前台的信息
    
    衍生操作：无

> Date: 2019.8.22
> Auth: Nightnessss

1.  行为：对全体`service`的数据操作添加异常报错
    
    目的：减少未知错误
    
    衍生操作：无
    
    问题：`ArticleDataServiceImpl`83-92行


