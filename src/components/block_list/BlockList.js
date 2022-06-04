import React, { useState, useEffect, memo, useCallback } from 'react';

import Block from '../block/Block';
import styles from './BlockList.module.css';

const BlockList = memo(({ blocks }) => {
  const [findBlock, setFindBlock] = useState(blocks);

  const blockComponentList = findBlock.map((block, index) => {
    return <Block id={block.id} index={index} blockContent={block.content} />;
  });
  return <div className={styles.grid}>{blockComponentList}</div>;
});

export default BlockList;
