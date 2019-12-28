package com.chart.domain.charting;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Caiqin
 * @Date: 2019/12/28 11:53
 */
@Data
@ToString
@Entity
@Table(name = "chart_book_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ChartBook implements Serializable{
    private static final long serialVersionUID = -916357110051689486L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String origin;
    private String owner;
    private String name;
    private String creator;
    private String version;
    private String category;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    private String description;
    @Column(name = "is_temp")
    private int isTemp;
    @Column(name = "icon_store")
    private String iconStore;
    @Column(name = "theme_id")
    private String themeId;
    @Column(name = "model_data")
    private String modelData;
    @Column(name = "model_compressed")
    private String modelCompressed;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "flag_domain")
    private int flagDomain;
    @Column(name = "private_in_public")
    private boolean privateInPublic;
    @Column(name = "book_type")
    private String bookType;
}
